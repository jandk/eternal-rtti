package be.twofold.rtti.darkages.classes;

import java.nio.ByteBuffer;

public record ClassTypeInfoRaw(
    long name,
    long superType,
    int superTypeTypeInfoToolsIndex,
    int nameHash,
    int size,
    long templateParms,
    long variables,
    long variableNameHashes,
    long classChecksum,
    long createInstance,
    long placementCreateInstance,
    long metaData
) {
    public static ClassTypeInfoRaw read(ByteBuffer buffer) {
        long name = buffer.getLong();
        long superType = buffer.getLong();
        int superTypeTypeInfoToolsIndex = buffer.getInt();
        int nameHash = buffer.getInt();
        int size = buffer.getInt();
        buffer.getInt(); // padding
        long templateParms = buffer.getLong();
        long variables = buffer.getLong();
        long variableNameHashes = buffer.getLong();
        long classChecksum = buffer.getLong();
        long createInstance = buffer.getLong();
        long placementCreateInstance = buffer.getLong();
        long metaData = buffer.getLong();

        return new ClassTypeInfoRaw(
            name,
            superType,
            superTypeTypeInfoToolsIndex,
            nameHash,
            size,
            templateParms,
            variables,
            variableNameHashes,
            classChecksum,
            createInstance,
            placementCreateInstance,
            metaData
        );
    }
}
