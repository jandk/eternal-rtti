package be.twofold.rtti.greatcircle.typedefs;

import java.nio.ByteBuffer;

public record TypedefInfoRaw(
    long name,
    long type,
    long ops,
    int size
) {
    public static TypedefInfoRaw read(ByteBuffer buffer) {
        var name = buffer.getLong();
        var type = buffer.getLong();
        var ops = buffer.getLong();
        var size = buffer.getInt();
        buffer.getInt(); // padding

        return new TypedefInfoRaw(
            name,
            type,
            ops,
            size
        );
    }
}
