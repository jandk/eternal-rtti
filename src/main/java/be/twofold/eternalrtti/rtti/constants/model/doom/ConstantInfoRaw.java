package be.twofold.eternalrtti.rtti.constants.model.doom;

import java.nio.*;

public record ConstantInfoRaw(
    long type,
    long name,
    long value
) {
    public static ConstantInfoRaw read(ByteBuffer buffer) {
        var type = buffer.getLong();
        var name = buffer.getLong();
        var value = buffer.getLong();

        return new ConstantInfoRaw(
            type,
            name,
            value
        );
    }
}
