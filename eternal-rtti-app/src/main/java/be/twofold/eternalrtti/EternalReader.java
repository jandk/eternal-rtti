package be.twofold.eternalrtti;

import be.twofold.eternalrtti.rtti.classes.model.*;
import be.twofold.eternalrtti.rtti.classes.model.eternal.*;
import be.twofold.eternalrtti.rtti.constants.model.*;
import be.twofold.eternalrtti.rtti.enums.model.*;
import be.twofold.eternalrtti.rtti.enums.model.eternal.*;
import be.twofold.eternalrtti.rtti.typedefs.model.*;
import be.twofold.eternalrtti.rtti.typedefs.model.eternal.*;
import be.twofold.eternalrtti.utils.*;

import java.util.*;

final class EternalReader implements RTTIReader {
    private final PeWrapper pe;

    EternalReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
    }

    @Override
    public List<ConstantInfo> readConstants() {
        return List.of();
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        var enums1 = new EnumReader(pe).read(0x243810, 0x281);
        var enums2 = new EnumReader(pe).read(0x8167b0, 0x5ce);

        var enums = new ArrayList<EnumTypeInfo>();
        enums.addAll(enums1);
        enums.addAll(enums2);
        return List.copyOf(enums);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        var classes1 = new ClassReader(pe).read(0x2628a0, 0xb3a);
        var classes2 = new ClassReader(pe).read(0x82b570, 0x244b);

        var classes = new ArrayList<ClassTypeInfo>();
        classes.addAll(classes1);
        classes.addAll(classes2);
        return List.copyOf(classes);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        var typedefs1 = new TypedefReader(pe).read(0x2950f0, 0x198);
        var typedefs2 = new TypedefReader(pe).read(0x8cea90, 0x153);

        var typedefs = new ArrayList<TypedefInfo>();
        typedefs.addAll(typedefs1);
        typedefs.addAll(typedefs2);
        return List.copyOf(typedefs);
    }
}
