package be.twofold.rtti.colossus.enums;

import java.nio.*;

public record EnumTypeInfoRaw(
    long name,
    long flags,
    long type,
    long values
) {
    public static EnumTypeInfoRaw read(ByteBuffer buffer) {
        var name = buffer.getLong();
        var flags = buffer.getLong();
        var type = buffer.getLong();
        var values = buffer.getLong();

        return new EnumTypeInfoRaw(
            name,
            flags,
            type,
            values
        );
    }
}
