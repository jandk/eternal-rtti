package be.twofold.eternalrtti;

import be.twofold.eternalrtti.rtti.classes.model.*;
import be.twofold.eternalrtti.rtti.classes.model.colossus.*;
import be.twofold.eternalrtti.rtti.constants.model.*;
import be.twofold.eternalrtti.rtti.constants.model.colossus.*;
import be.twofold.eternalrtti.rtti.enums.model.*;
import be.twofold.eternalrtti.rtti.enums.model.colossus.*;
import be.twofold.eternalrtti.rtti.typedefs.model.*;
import be.twofold.eternalrtti.rtti.typedefs.model.colossus.*;
import be.twofold.eternalrtti.utils.*;

import java.util.*;

final class NewColossusReader implements RTTIReader {
    private static final long RDATA = 0x141E641B8L;

    private final PeWrapper pe;

    NewColossusReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
    }

    @Override
    public List<ConstantInfo> readConstants() {
        return new ConstantReader(pe).read(0x142479DB0L, 0x3A31);
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        return new EnumReader(pe).read(0x1424D1920L, 0x6BB);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        return new ClassReader(pe).read(0x14251C070L, 0x20B3);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        return new TypedefReader(pe).read(0x1425C1830L, 0x1CA);
    }
}
