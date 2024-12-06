package be.twofold.rtti.greatcircle.enums;

import java.nio.ByteBuffer;

public record EnumTypeInfoRaw(
    long name,
    long flags,
    int type,
    int valueIndexLength,
    long values,
    long valueIndex
) {
    public static EnumTypeInfoRaw read(ByteBuffer buffer) {
        var name = buffer.getLong();
        var flags = buffer.getLong();
        var type = buffer.getInt();
        var valueIndexLength = buffer.getInt();
        var values = buffer.getLong();
        var valueIndex = buffer.getLong();

        return new EnumTypeInfoRaw(
            name,
            flags,
            type,
            valueIndexLength,
            values,
            valueIndex
        );
    }
}
