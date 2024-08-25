package be.twofold.eternalrtti.rtti.classes.model.doom;

import java.nio.*;

public record ClassVariableInfoRaw(
    long type,
    long ops,
    long name,
    int offset,
    int size,
    int flags,
    long comment,
    long get,
    long set,
    long reallocate
) {
    public static ClassVariableInfoRaw read(ByteBuffer buffer) {
        var type = buffer.getLong();
        var ops = buffer.getLong();
        var name = buffer.getLong();
        var offset = buffer.getInt();
        var size = buffer.getInt();
        var flags = buffer.getInt();
        buffer.getInt(); // padding
        var comment = buffer.getLong();
        var get = buffer.getLong();
        var set = buffer.getLong();
        var reallocate = buffer.getLong();

        return new ClassVariableInfoRaw(
            type,
            ops,
            name,
            offset,
            size,
            flags,
            comment,
            get,
            set,
            reallocate
        );
    }
}
