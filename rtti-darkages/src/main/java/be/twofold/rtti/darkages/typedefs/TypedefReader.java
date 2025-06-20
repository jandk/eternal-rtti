package be.twofold.rtti.darkages.typedefs;

import be.twofold.rtti.common.typedefs.TypedefInfo;
import be.twofold.rtti.common.utils.BufferUtils;
import be.twofold.rtti.common.utils.PeWrapper;

import java.util.List;

public final class TypedefReader {
    private final PeWrapper pe;

    public TypedefReader(PeWrapper pe) {
        this.pe = pe;
    }

    public List<TypedefInfo> read(long offset, int count) {
        var types = BufferUtils.readStructs(
            pe.getBuffer(offset),
            count - 1,
            TypedefInfoRaw::read
        );

        return types.stream()
            .map(this::map)
            .toList();
    }

    private TypedefInfo map(TypedefInfoRaw raw) {
        var name = pe.getCString(raw.name());
        var type = pe.getCString(raw.type());
        var ops = pe.getCString(raw.ops());
        return new TypedefInfo(name, type, ops);
    }
}
