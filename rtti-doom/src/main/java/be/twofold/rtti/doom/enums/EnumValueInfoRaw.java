package be.twofold.rtti.doom.enums;

import java.nio.*;

public record EnumValueInfoRaw(
    long name,
    int value
) {
    public static EnumValueInfoRaw read(ByteBuffer buffer) {
        var name = buffer.getLong();
        var value = buffer.getInt();
        buffer.getInt(); // padding

        return new EnumValueInfoRaw(
            name,
            value
        );
    }
}
