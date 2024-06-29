package be.twofold.eternalrtti.rtti.constants.model.colossus;

import be.twofold.eternalrtti.rtti.constants.model.*;
import be.twofold.eternalrtti.utils.*;

import java.util.*;

public final class ConstantReader {
    private final PeWrapper pe;

    public ConstantReader(PeWrapper pe) {
        this.pe = pe;
    }

    public List<ConstantInfo> read(long offset, int count) {
        var types = BufferUtils.readStructs(
            pe.getBuffer(offset),
            count - 1,
            ConstantInfoRaw::read
        );

        return types.stream()
            .map(this::map)
            .toList();
    }

    private ConstantInfo map(ConstantInfoRaw raw) {
        var type = pe.getCString(raw.type());
        var name = pe.getCString(raw.name());
        var value = pe.getCString(raw.value());
        return new ConstantInfo(type, name, value);
    }
}
