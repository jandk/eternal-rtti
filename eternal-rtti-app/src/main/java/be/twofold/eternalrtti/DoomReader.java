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
        return new ConstantReader(pe).read(0x5642a0, 0x4201);
    }

    @Override
    public List<EnumTypeInfo> readEnums() {
        return new EnumReader(pe).read(0x5c9360, 0x68e);
    }

    @Override
    public List<ClassTypeInfo> readClasses() {
        return new ClassReader(pe).read(0x614ce0, 0x27e2);
    }

    @Override
    public List<TypedefInfo> readTypedefs() {
        return new TypedefReader(pe).read(0x6a09f0, 0x23b);
    }
}
