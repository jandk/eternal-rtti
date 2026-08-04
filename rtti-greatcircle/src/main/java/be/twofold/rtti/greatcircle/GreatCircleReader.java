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
        var enums1 = new EnumReader(pe).read(0x143051510L, 0x302);
        var enums2 = new EnumReader(pe).read(0x1434F9080L, 0x6D1);

        var enums = new ArrayList<EnumTypeInfo>();
        enums.addAll(enums1);
        enums.addAll(enums2);
        return List.copyOf(enums);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        var classes1 = new ClassReader(pe).read(0x143058DC0L, 0x0F91);
        var classes2 = new ClassReader(pe).read(0x14350A130L, 0x28C4);

        var classes = new ArrayList<ClassTypeInfo>();
        classes.addAll(classes1);
        classes.addAll(classes2);
        return List.copyOf(classes);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        var typedefs1 = new TypedefReader(pe).read(0x1430AE7D0L, 0x24B);
        var typedefs2 = new TypedefReader(pe).read(0x1435EA4E0L, 0x69F);

        var typedefs = new ArrayList<TypedefInfo>();
        typedefs.addAll(typedefs1);
        typedefs.addAll(typedefs2);
        return List.copyOf(typedefs);
    }
}
