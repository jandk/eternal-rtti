package be.twofold.eternalrtti.rtti.enums.model.doom;

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
