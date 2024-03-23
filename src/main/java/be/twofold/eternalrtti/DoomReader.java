package be.twofold.eternalrtti;

import be.twofold.eternalrtti.rtti.classes.model.*;
import be.twofold.eternalrtti.rtti.classes.model.doom.*;
import be.twofold.eternalrtti.rtti.constants.model.*;
import be.twofold.eternalrtti.rtti.constants.model.doom.*;
import be.twofold.eternalrtti.rtti.enums.model.*;
import be.twofold.eternalrtti.rtti.enums.model.doom.*;
import be.twofold.eternalrtti.rtti.typedefs.model.*;
import be.twofold.eternalrtti.rtti.typedefs.model.doom.*;
import be.twofold.eternalrtti.utils.*;

import java.util.*;

final class DoomReader implements RTTIReader {
    private final PeWrapper pe;

    DoomReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
    }

    @Override
    public List<ConstantInfo> readConstants() {
        return new ConstantReader(pe).read(0x779b50, 0x41c7);
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        return new EnumReader(pe).read(0x7e5ff0, 0x67f);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        return new ClassReader(pe).read(0x829ce0, 0x27cf);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        return new TypedefReader(pe).read(0x8b53a0, 0x1eb);
    }
}
