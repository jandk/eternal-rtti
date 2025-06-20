package be.twofold.rtti.darkages.enums;

import be.twofold.rtti.common.enums.EnumType;
import be.twofold.rtti.common.enums.EnumTypeInfo;
import be.twofold.rtti.common.enums.EnumValueInfo;
import be.twofold.rtti.common.utils.BufferUtils;
import be.twofold.rtti.common.utils.PeWrapper;
import be.twofold.rtti.darkages.classes.SpecifierFlagDarkAges;

import java.nio.*;
import java.util.*;
import java.util.stream.*;

public final class EnumReader {
    private final PeWrapper pe;

    public EnumReader(PeWrapper pe) {
        this.pe = pe;
    }

    public List<EnumTypeInfo> read(long offset, int count) {
        var types = BufferUtils.readStructs(
            pe.getBuffer(offset),
            count - 1,
            EnumTypeInfoRaw::read
        );

        List<EnumTypeInfo> list = new ArrayList<>();
        for (int i = 0; i < types.size(); i++) {
            EnumTypeInfoRaw type = types.get(i);
            EnumTypeInfo map = map(type);
            list.add(map);
        }
        return list;
    }

    private EnumTypeInfo map(EnumTypeInfoRaw raw) {
        var name = pe.getCString(raw.name());
        var flags = SpecifierFlagDarkAges.fromValue(raw.flags());
        var type = EnumType.fromValue(raw.type());
        var values = readValues(raw);
        return new EnumTypeInfo(name, (Set) flags, type, values, raw.nameHash());
    }

    private List<EnumValueInfo> readValues(EnumTypeInfoRaw type) {
        if (type.valueIndexLength() == 0) {
            return List.of();
        }

        var enumValueInfoRaws = BufferUtils.readStructs(
            pe.getBuffer(type.values()),
            type.valueIndexLength(),
            EnumValueInfoRaw::read
        );
        var valueNameHashes = readValueNameHashes(type);

        return IntStream.range(0, type.valueIndexLength())
            .mapToObj(i -> {
                var enumValueInfoRaw = enumValueInfoRaws.get(i);
                var valueNameHash = valueNameHashes.get(i);
                return mapValue(enumValueInfoRaw, valueNameHash);
            })
            .toList();
    }

    private List<Long> readValueNameHashes(EnumTypeInfoRaw type) {
        if (type.valueIndexLength() == 0) {
            return List.of();
        }

        return BufferUtils.readStructs(
            pe.getBuffer(type.valueNameHashes()),
            type.valueIndexLength(),
            ByteBuffer::getLong
        );
    }

    private EnumValueInfo mapValue(EnumValueInfoRaw raw, long nameHash) {
        var name = pe.getCString(raw.name());
        return new EnumValueInfo(name, raw.value(), null, nameHash);
    }
}
