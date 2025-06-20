package be.twofold.rtti.darkages.enums;

import java.nio.ByteBuffer;

public record EnumValueInfoRaw(
    long name,
    long value
) {
    public static EnumValueInfoRaw read(ByteBuffer buffer) {
        var name = buffer.getLong();
        var value = buffer.getLong();

        return new EnumValueInfoRaw(
            name,
            value
        );
    }
}
