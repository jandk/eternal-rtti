package be.twofold.rtti.rage;

import be.twofold.rtti.common.*;
import be.twofold.rtti.common.classes.*;
import be.twofold.rtti.common.constants.*;
import be.twofold.rtti.common.enums.*;
import be.twofold.rtti.common.typedefs.*;
import be.twofold.rtti.common.utils.*;
import be.twofold.rtti.rage.classes.*;
import be.twofold.rtti.rage.constants.*;
import be.twofold.rtti.rage.enums.*;
import be.twofold.rtti.rage.typedefs.*;

import java.util.*;

public final class RageReader implements RTTIReader {
    private final PeWrapper pe;

    public RageReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
    }

    @Override
    public List<ConstantInfo> readConstants() {
        return new ConstantReader(pe).read(0x141FF42B0L, 0x1E66);
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        return new EnumReader(pe).read(0x142041C60L, 0x2DD);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        return new ClassReader(pe).read(0x141FAC7C0L, 0x147B);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        return new TypedefReader(pe).read(0x142021C50L, 0xD2);
    }
}
