package be.twofold.rtti.neworder.enums;

import java.nio.*;

public record EnumTypeInfoRaw(
    long name,
    int flags,
    long values
) {
    public static EnumTypeInfoRaw read(ByteBuffer buffer) {
        var name = buffer.getLong();
        var flags = buffer.getInt();
        buffer.getInt(); // padding
        var values = buffer.getLong();

        return new EnumTypeInfoRaw(
            name,
            flags,
            values
        );
    }
}
