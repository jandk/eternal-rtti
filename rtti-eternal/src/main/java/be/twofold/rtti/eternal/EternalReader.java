package be.twofold.rtti.eternal;

import be.twofold.rtti.common.*;
import be.twofold.rtti.common.classes.*;
import be.twofold.rtti.common.constants.*;
import be.twofold.rtti.common.enums.*;
import be.twofold.rtti.common.typedefs.*;
import be.twofold.rtti.common.utils.*;
import be.twofold.rtti.eternal.classes.*;
import be.twofold.rtti.eternal.enums.*;
import be.twofold.rtti.eternal.typedefs.*;

import java.util.*;

public final class EternalReader implements RTTIReader {
    private final PeWrapper pe;

    public EternalReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
    }

    @Override
    public List<ConstantInfo> readConstants() {
        return List.of();
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        var enums1 = new EnumReader(pe).read(0x143abe780L, 0x282);
        var enums2 = new EnumReader(pe).read(0x144091140L, 0x5ce);

        var enums = new ArrayList<EnumTypeInfo>();
        enums.addAll(enums1);
        enums.addAll(enums2);
        return List.copyOf(enums);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        var classes1 = new ClassReader(pe).read(0x143add800L, 0x0b3a);
        var classes2 = new ClassReader(pe).read(0x1440a60f0L, 0x244b);

        var classes = new ArrayList<ClassTypeInfo>();
        classes.addAll(classes1);
        classes.addAll(classes2);
        return List.copyOf(classes);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        var typedefs1 = new TypedefReader(pe).read(0x143b10050L, 0x198);
        var typedefs2 = new TypedefReader(pe).read(0x144149610L, 0x153);

        var typedefs = new ArrayList<TypedefInfo>();
        typedefs.addAll(typedefs1);
        typedefs.addAll(typedefs2);
        return List.copyOf(typedefs);
    }
}
