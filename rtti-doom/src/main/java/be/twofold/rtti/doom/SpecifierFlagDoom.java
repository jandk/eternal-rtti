package be.twofold.rtti.doom;

import be.twofold.rtti.common.*;

import java.util.*;

public enum SpecifierFlagDoom implements SpecifierFlag {
    SPECIFIERFLAG_CONST(0x1),
    SPECIFIERFLAG_VOLATILE(0x2),
    SPECIFIERFLAG_AUTO(0x4),
    SPECIFIERFLAG_REGISTER(0x8),
    SPECIFIERFLAG_STATIC(0x10),
    SPECIFIERFLAG_EXTERN(0x20),
    SPECIFIERFLAG_MUTABLE(0x40),
    SPECIFIERFLAG_INLINE(0x80),
    SPECIFIERFLAG_VIRTUAL(0x100),
    SPECIFIERFLAG_EXPLICIT(0x200),
    SPECIFIERFLAG_FRIEND(0x400),
    SPECIFIERFLAG_TYPEDEF(0x800),
    SPECIFIERFLAG_METASTATE(0x1000),
    SPECIFIERFLAG_ALLOCATOR(0x2000),
    SPECIFIERFLAG_SAVESKIP(0x4000),
    SPECIFIERFLAG_SAVEOBJ(0x8000),
    SPECIFIERFLAG_EDIT(0x10000),
    SPECIFIERFLAG_DESIGN(0x20000),
    SPECIFIERFLAG_DEF(0x40000),
    SPECIFIERFLAG_ENUMBITFLAGS(0x80000),
    SPECIFIERFLAG_SCRIPTDEFINE(0x100000),
    SPECIFIERFLAG_BOLD(0x200000),
    SPECIFIERFLAG_META(0x400000),
    SPECIFIERFLAG_HIDDEN(0x800000),
    SPECIFIERFLAG_LOADSKIP(0x1000000),
    SPECIFIERFLAG_PURE(0x2000000),
    SPECIFIERFLAG_DISPLAY(0x4000000);

    private final long value;

    SpecifierFlagDoom(long value) {
        this.value = value;
    }

    public static Set<SpecifierFlagDoom> fromValue(long value) {
        Set<SpecifierFlagDoom> flags = EnumSet.noneOf(SpecifierFlagDoom.class);
        for (var flag : values()) {
            if ((value & flag.value) == flag.value) {
                flags.add(flag);
            }
        }
        return flags;
    }
}
