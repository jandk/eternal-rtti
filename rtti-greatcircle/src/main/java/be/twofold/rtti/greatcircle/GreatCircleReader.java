package be.twofold.rtti.greatcircle;

import be.twofold.rtti.common.*;
import be.twofold.rtti.common.classes.*;
import be.twofold.rtti.common.constants.*;
import be.twofold.rtti.common.enums.*;
import be.twofold.rtti.common.typedefs.*;
import be.twofold.rtti.common.utils.*;
import be.twofold.rtti.greatcircle.classes.*;
import be.twofold.rtti.greatcircle.enums.*;
import be.twofold.rtti.greatcircle.typedefs.*;

import java.util.*;

public final class GreatCircleReader implements RTTIReader {
    private final PeWrapper pe;

    public GreatCircleReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
    }

    @Override
    public List<ConstantInfo> readConstants() {
        return List.of();
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        var enums1 = new EnumReader(pe).read(0x142F4FDC0L, 0x2FA);
        var enums2 = new EnumReader(pe).read(0x143416FA0L, 0x6BC);

        var enums = new ArrayList<EnumTypeInfo>();
        enums.addAll(enums1);
        enums.addAll(enums2);
        return List.copyOf(enums);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        var classes1 = new ClassReader(pe).read(0x142F859F0L, 0xF79);
        var classes2 = new ClassReader(pe).read(0x143427D00L, 0x282E);

        var classes = new ArrayList<ClassTypeInfo>();
        classes.addAll(classes1);
        classes.addAll(classes2);
        return List.copyOf(classes);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        var typedefs1 = new TypedefReader(pe).read(0x142FDAC10L, 0x248);
        var typedefs2 = new TypedefReader(pe).read(0x143504D20L, 0x682);

        var typedefs = new ArrayList<TypedefInfo>();
        typedefs.addAll(typedefs1);
        typedefs.addAll(typedefs2);
        return List.copyOf(typedefs);
    }
}
