package be.twofold.rtti.doom.enums;

import be.twofold.rtti.common.enums.*;
import be.twofold.rtti.common.utils.*;

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

        return types.stream()
            .map(this::map)
            .toList();
    }

    private EnumTypeInfo map(EnumTypeInfoRaw raw) {
        var name = pe.getCString(raw.name());
        var values = readValues(raw);
        return new EnumTypeInfo(name, Set.of(), EnumType.ENUM_S32, values);
    }

    private List<EnumValueInfo> readValues(EnumTypeInfoRaw type) {
        if (type.values() == 0) {
            return List.of();
        }

        return pe.getBufferOptional(type.values())
            .map(byteBuffer -> Stream
                .generate(() -> EnumValueInfoRaw.read(byteBuffer))
                .takeWhile(raw -> raw.name() != 0)
                .map(this::mapValue)
                .toList())
            .orElseGet(List::of);
    }

    private EnumValueInfo mapValue(EnumValueInfoRaw raw) {
        var name = pe.getCString(raw.name());
        return new EnumValueInfo(name, raw.value(), null);
    }
}
