package be.twofold.rtti.greatcircle.enums;

import java.nio.*;

public record EnumValueInfoRaw(
    long name,
    long value,
    long comment
) {
    public static EnumValueInfoRaw read(ByteBuffer buffer) {
        var name = buffer.getLong();
        var value = buffer.getLong();
        var comment = buffer.getLong();

        return new EnumValueInfoRaw(
            name,
            value,
            comment
        );
    }
}
