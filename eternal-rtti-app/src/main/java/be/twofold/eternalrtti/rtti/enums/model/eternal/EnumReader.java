package be.twofold.eternalrtti.rtti.enums.model.eternal;

import be.twofold.eternalrtti.rtti.base.*;
import be.twofold.rtti.common.enums.*;
import be.twofold.rtti.common.utils.*;

import java.util.*;

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

        return types.stream()
            .map(this::map)
            .toList();
    }

    private EnumTypeInfo map(EnumTypeInfoRaw raw) {
        var name = pe.getCString(raw.name());
        var flags = SpecifierFlagEternal.fromValue(raw.flags());
        var type = EnumType.fromValue(raw.type());
        var values = readValues(raw);
        return new EnumTypeInfo(name, (Set) flags, type, values);
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

        return enumValueInfoRaws.stream()
            .map(this::mapValue)
            .toList();
    }

    private EnumValueInfo mapValue(EnumValueInfoRaw raw) {
        var name = pe.getCString(raw.name());
        return new EnumValueInfo(name, raw.value());
    }
}
