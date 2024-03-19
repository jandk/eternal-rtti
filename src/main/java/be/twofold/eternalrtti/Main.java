package be.twofold.eternalrtti;

import be.twofold.eternalrtti.rtti.classes.*;
import be.twofold.eternalrtti.rtti.enums.*;
import be.twofold.eternalrtti.utils.*;
import com.kichik.pecoff4j.*;
import com.kichik.pecoff4j.io.*;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

public final class Main {
    private static final String HASH = "7352dc4da0b107939c7d4d1e2c42ea087c566c26e79f8e01270678a0c61f6b39";
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
        if (!hash.equals(HASH)) {
            System.out.println("Invalid file hash: " + hash + " (expected: " + HASH + ")");
            return;
        }

        var dataSection = args.length == 2 ? Files.readAllBytes(Path.of(args[1])) : null;

        var pe = new PeWrapper(readPE(buffer), dataSection);
        var enums1 = new EnumReader(pe).read(0x243810, 0x281);
        var enums2 = new EnumReader(pe).read(0x8167b0, 0x5ce);
        var classes1 = new ClassReader(pe).read(0x2628a0, 0xb3a);
        var classes2 = new ClassReader(pe).read(0x82b570, 0x244b);
        // var typedefs1 = new TypedefReader(pe).read(0x2950f0, 0x198);
        // var typedefs2 = new TypedefReader(pe).read(0x8cea90, 0x153);

        try (var writer = Files.newBufferedWriter(Path.of("idLib.h"))) {
            var enumWriter = new EnumWriter();
            enumWriter.write(enums1);
            enumWriter.write(enums2);
            writer.write(enumWriter.toString());

            var classWriter = new ClassWriter();
            classWriter.write(classes1);
            classWriter.write(classes2);
            writer.write(classWriter.toString());
        }
    }

    private static PE readPE(byte[] buffer) throws IOException {
        try (var reader = new DataReader(buffer)) {
            return PE.read(reader);
        }
    }
}
