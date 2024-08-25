package be.twofold.rtti.neworder;

import be.twofold.rtti.common.*;
import be.twofold.rtti.common.classes.*;
import be.twofold.rtti.common.constants.*;
import be.twofold.rtti.common.enums.*;
import be.twofold.rtti.common.typedefs.*;
import be.twofold.rtti.common.utils.*;
import be.twofold.rtti.neworder.classes.*;
import be.twofold.rtti.neworder.constants.*;
import be.twofold.rtti.neworder.enums.*;
import be.twofold.rtti.neworder.typedefs.*;

import java.util.*;

public final class NewOrderReader implements RTTIReader {
    private final PeWrapper pe;

    public NewOrderReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
    }

    @Override
    public List<ConstantInfo> readConstants() {
        return new ConstantReader(pe).read(0x1414cafa0L, 0x256c);
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        return new EnumReader(pe).read(0x1415293a0L, 0x3de);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        return new ClassReader(pe).read(0x14153e100L, 0x13cf);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        return new TypedefReader(pe).read(0x141583650L, 0x10c);
    }
}
