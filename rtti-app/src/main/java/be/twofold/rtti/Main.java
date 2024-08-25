package be.twofold.rtti;

import be.twofold.rtti.common.classes.*;
import be.twofold.rtti.common.constants.*;
import be.twofold.rtti.common.enums.*;
import be.twofold.rtti.common.typedefs.*;
import be.twofold.rtti.common.utils.*;
import com.kichik.pecoff4j.*;
import com.kichik.pecoff4j.io.*;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

public final class Main {
    private static final MessageDigest SHA256;

    static {
        try {
            SHA256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java -jar eternal-rtti.jar <path to DOOMEternalx64vk.exe>");
            return;
        }

        if (!Files.exists(Path.of(args[0]))) {
            System.out.println("File does not exist");
            return;
        }

        var buffer = Files.readAllBytes(Path.of(args[0]));

        var hash = HexFormat.of().formatHex(SHA256.digest(buffer));
        var game = Game.fromHash(hash);

        var dataSection = args.length == 2 ? Files.readAllBytes(Path.of(args[1])) : null;

        var pe = new PeWrapper(readPE(buffer), dataSection);
        var reader = game.reader().apply(pe);

        var constants = reader.readConstants();
        var enums = reader.readEnums();
        var classes = reader.readClasses();
        var typedefs = reader.readTypedefs();

        try (var writer = Files.newBufferedWriter(Path.of(game + "-rtti.h"))) {
            writer.write(new ConstantWriter().write(constants).toString());
            writer.write('\n');
            writer.write(new EnumWriter().write(enums).toString());
            writer.write('\n');
            writer.write(new ClassWriter().write(classes).toString());
            writer.write('\n');
            writer.write(new TypedefWriter().write(typedefs).toString());
            writer.write('\n');
        }
    }

    private static PE readPE(byte[] buffer) throws IOException {
        try (var reader = new DataReader(buffer)) {
            return PE.read(reader);
        }
    }
}
