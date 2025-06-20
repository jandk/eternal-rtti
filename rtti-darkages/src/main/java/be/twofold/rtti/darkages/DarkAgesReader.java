package be.twofold.rtti.darkages;

import be.twofold.rtti.common.RTTIReader;
import be.twofold.rtti.common.classes.ClassTypeInfo;
import be.twofold.rtti.common.constants.ConstantInfo;
import be.twofold.rtti.common.enums.EnumTypeInfo;
import be.twofold.rtti.common.typedefs.TypedefInfo;
import be.twofold.rtti.common.utils.PeWrapper;
import be.twofold.rtti.darkages.classes.ClassReader;
import be.twofold.rtti.darkages.enums.EnumReader;
import be.twofold.rtti.darkages.typedefs.TypedefReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class DarkAgesReader implements RTTIReader {
    private final PeWrapper pe;

    public DarkAgesReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
    }

    @Override
    public List<ConstantInfo> readConstants() {
        return List.of();
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        var enums1 = new EnumReader(pe).read(0x143542160L, 0x36D);
        var enums2 = new EnumReader(pe).read(0x143A148C0L, 0x5BC);

        var enums = new ArrayList<EnumTypeInfo>();
        enums.addAll(enums1);
        enums.addAll(enums2);
        return List.copyOf(enums);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        var classes1 = new ClassReader(pe).read(0x1464C99E0L, 0x1036);
        var classes2 = new ClassReader(pe).read(0x146D20FA0L, 0x3BD2);

        var classes = new ArrayList<ClassTypeInfo>();
        classes.addAll(classes1);
        classes.addAll(classes2);
        return List.copyOf(classes);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        var typedefs1 = new TypedefReader(pe).read(0x143589560L, 0x27A);
        var typedefs2 = new TypedefReader(pe).read(0x143ACD450L, 0x5CC);

        var typedefs = new ArrayList<TypedefInfo>();
        typedefs.addAll(typedefs1);
        typedefs.addAll(typedefs2);
        return List.copyOf(typedefs);
    }
}
