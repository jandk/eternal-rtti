package be.twofold.eternalrtti;

import be.twofold.eternalrtti.rtti.classes.*;
import be.twofold.eternalrtti.rtti.constants.*;
import be.twofold.eternalrtti.rtti.enums.*;
import be.twofold.eternalrtti.rtti.typedefs.*;
import be.twofold.eternalrtti.utils.*;
import com.kichik.pecoff4j.*;
import com.kichik.pecoff4j.io.*;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

public final class Main {
    private static final String HASH_DOOM = "ffd0ba84ad5f296603927a576ac5ce6c683a3a6d8fafc26b50b28db2082abc03";
    private static final String HASH_ETERNAL = "7352dc4da0b107939c7d4d1e2c42ea087c566c26e79f8e01270678a0c61f6b39";

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
        switch (hash) {
            case HASH_DOOM -> System.out.println("DOOM 2016 detected");
            case HASH_ETERNAL -> System.out.println("DOOM Eternal detected");
            default -> {
                System.out.println("Invalid file hash: " + hash + " (expected: " + HASH_DOOM + " or " + HASH_ETERNAL + ")");
                return;
            }
        }

        var dataSection = args.length == 2 ? Files.readAllBytes(Path.of(args[1])) : null;

        var pe = new PeWrapper(readPE(buffer), dataSection);
        var reader = switch (hash) {
            case HASH_DOOM -> new DoomReader(pe);
            case HASH_ETERNAL -> new EternalReader(pe);
            default -> throw new IllegalStateException("Unexpected value: " + hash);
        };

        var constants = reader.readConstants();
        var enums = reader.readEnums();
        var classes = reader.readClasses();
        var typedefs = reader.readTypedefs();

        try (var writer = Files.newBufferedWriter(Path.of("doom-idLib.h"))) {
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
