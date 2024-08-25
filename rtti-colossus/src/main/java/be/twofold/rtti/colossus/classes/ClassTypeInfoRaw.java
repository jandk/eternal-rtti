package be.twofold.rtti.colossus.classes;

import java.nio.*;

public record ClassTypeInfoRaw(
    long name,
    long superType,
    int size,
    long typeId,
    long templateParms,
    long variables,
    long createInstance,
    long createModel,
    long metaData
) {
    public static ClassTypeInfoRaw read(ByteBuffer buffer) {
        var name = buffer.getLong();
        var superType = buffer.getLong();
        var size = buffer.getInt();
        buffer.getInt(); // padding
        var typeId = buffer.getLong();
        var templateParms = buffer.getLong();
        var variables = buffer.getLong();
        var createInstance = buffer.getLong();
        var createModel = buffer.getLong();
        var metaData = buffer.getLong();

        return new ClassTypeInfoRaw(
            name,
            superType,
            size,
            typeId,
            templateParms,
            variables,
            createInstance,
            createModel,
            metaData
        );
    }
}
