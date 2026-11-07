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

public final class EternalStudioReader implements RTTIReader {
    private final PeWrapper pe;

    public EternalStudioReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
    }

    @Override
    public List<ConstantInfo> readConstants() {
        return List.of();
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        var enums1 = new EnumReader(pe).read(0x1461E1090L, 0x286);
        var enums2 = new EnumReader(pe).read(0x1467A8560L, 0x5E1);

        var enums = new ArrayList<EnumTypeInfo>();
        enums.addAll(enums1);
        enums.addAll(enums2);
        return List.copyOf(enums);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        var classes1 = new ClassReader(pe).read(0x1461FDB70L, 0x0B68);
        var classes2 = new ClassReader(pe).read(0x1467D0480L, 0x2494);

        var classes = new ArrayList<ClassTypeInfo>();
        classes.addAll(classes1);
        classes.addAll(classes2);
        return List.copyOf(classes);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        var typedefs1 = new TypedefReader(pe).read(0x1462310B0L, 0x1A1);
        var typedefs2 = new TypedefReader(pe).read(0x146874E30L, 0x162);

        var typedefs = new ArrayList<TypedefInfo>();
        typedefs.addAll(typedefs1);
        typedefs.addAll(typedefs2);
        return List.copyOf(typedefs);
    }
}
