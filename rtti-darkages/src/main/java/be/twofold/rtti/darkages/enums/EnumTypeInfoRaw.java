package be.twofold.rtti.darkages.enums;

import java.nio.ByteBuffer;

public record EnumTypeInfoRaw(
    long name,
    long flags,
    int type,
    int nameHash,
    int valueIndexLength,
    long values,
    long valueNameHashes,
    long enumChecksum,
    long valueIndex
) {
    public static EnumTypeInfoRaw read(ByteBuffer buffer) {
        var name = buffer.getLong();
        var flags = buffer.getLong();
        var type = buffer.getInt();
        var nameHash = buffer.getInt();
        var valueIndexLength = buffer.getInt();
        buffer.getInt(); // padding
        var values = buffer.getLong();
        var valueNameHashes = buffer.getLong();
        var enumChecksum = buffer.getLong();
        var valueIndex = buffer.getLong();

        return new EnumTypeInfoRaw(
            name,
            flags,
            type,
            nameHash,
            valueIndexLength,
            values,
            valueNameHashes,
            enumChecksum,
            valueIndex
        );
    }
}
