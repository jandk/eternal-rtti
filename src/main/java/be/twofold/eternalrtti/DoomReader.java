package be.twofold.eternalrtti;

import be.twofold.eternalrtti.rtti.classes.model.*;
import be.twofold.eternalrtti.rtti.classes.model.doom.*;
import be.twofold.eternalrtti.rtti.enums.model.*;
import be.twofold.eternalrtti.rtti.enums.model.doom.*;
import be.twofold.eternalrtti.rtti.typedefs.*;
import be.twofold.eternalrtti.rtti.typedefs.model.*;
import be.twofold.eternalrtti.utils.*;

import java.util.*;

final class DoomReader implements RTTIReader {
    private final PeWrapper pe;

    DoomReader(PeWrapper pe) {
        this.pe = Objects.requireNonNull(pe);
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
        var typedefs1 = new TypedefReader(pe).read(0x2950f0, 0x198);
        var typedefs2 = new TypedefReader(pe).read(0x8cea90, 0x153);

        var typedefs = new ArrayList<TypedefInfo>();
        typedefs.addAll(typedefs1);
        typedefs.addAll(typedefs2);
        return List.copyOf(typedefs);
    }
}
