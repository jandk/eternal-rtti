package be.twofold.rtti.doom;

import be.twofold.rtti.common.*;
import be.twofold.rtti.common.classes.*;
import be.twofold.rtti.common.constants.*;
import be.twofold.rtti.common.enums.*;
import be.twofold.rtti.common.typedefs.*;
import be.twofold.rtti.common.utils.*;
import be.twofold.rtti.doom.classes.*;
import be.twofold.rtti.doom.constants.*;
import be.twofold.rtti.doom.enums.*;
import be.twofold.rtti.doom.typedefs.*;

import java.util.*;

public final class DoomVkReader implements RTTIReader {
    private final PeWrapper pe;

    public DoomVkReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
    }

    @Override
    public List<ConstantInfo> readConstants() {
        return new ConstantReader(pe).read(0x1434A92A0L, 0x4201);
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        return new EnumReader(pe).read(0x14350E360L, 0x68e);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        return new ClassReader(pe).read(0x143559CE0L, 0x27e2);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        return new TypedefReader(pe).read(0x1435E59F0L, 0x23b);
    }
}
