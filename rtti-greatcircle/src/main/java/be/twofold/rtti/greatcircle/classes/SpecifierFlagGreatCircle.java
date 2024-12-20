package be.twofold.rtti.greatcircle.classes;

import be.twofold.rtti.common.*;

import java.util.*;

public enum SpecifierFlagGreatCircle implements SpecifierFlag {
    SPECIFIERFLAG_CONST(0x1L),
    SPECIFIERFLAG_VOLATILE(0x2L),
    SPECIFIERFLAG_AUTO(0x4L),
    SPECIFIERFLAG_REGISTER(0x8L),
    SPECIFIERFLAG_STATIC(0x10L),
    SPECIFIERFLAG_EXTERN(0x20L),
    SPECIFIERFLAG_MUTABLE(0x40L),
    SPECIFIERFLAG_INLINE(0x80L),
    SPECIFIERFLAG_VIRTUAL(0x100L),
    SPECIFIERFLAG_EXPLICIT(0x200L),
    SPECIFIERFLAG_FRIEND(0x400L),
    SPECIFIERFLAG_TYPEDEF(0x800L),
    SPECIFIERFLAG_METASTATE(0x1000L),
    SPECIFIERFLAG_UNINITIALIZEDTYPE(0x2000L),
    SPECIFIERFLAG_SAVESKIP(0x4000L),
    SPECIFIERFLAG_DEPRECATED(0x8000L),
    SPECIFIERFLAG_EDIT(0x10000L),
    SPECIFIERFLAG_DESIGN(0x20000L),
    SPECIFIERFLAG_DEF(0x40000L),
    SPECIFIERFLAG_ENUMBITFLAGS(0x80000L),
    SPECIFIERFLAG_SCRIPTDEFINE(0x100000L),
    SPECIFIERFLAG_BOLD(0x200000L),
    SPECIFIERFLAG_META(0x400000L),
    SPECIFIERFLAG_HIDDEN(0x800000L),
    SPECIFIERFLAG_EXPORTED(0x1000000L),
    SPECIFIERFLAG_PURE(0x2000000L),
    SPECIFIERFLAG_DISPLAY(0x4000000L),
    SPECIFIERFLAG_NO_SPAWN(0x8000000L),
    SPECIFIERFLAG_NODEINPUT(0x10000000L),
    SPECIFIERFLAG_NODEOUTPUT(0x20000000L),
    SPECIFIERFLAG_PARM_MUTABLE(0x40000000L),
    SPECIFIERFLAG_NOCOMMENT(0x80000000L),
    SPECIFIERFLAG_EXTERN_TEMPLATE(0x100000000L),
    SPECIFIERFLAG_DELETE(0x200000000L),
    SPECIFIERFLAG_DEFAULT(0x400000000L),
    SPECIFIERFLAG_NOTNULL(0x800000000L),
    SPECIFIERFLAG_ENUMSKIPLAST(0x1000000000L),
    SPECIFIERFLAG_EDITABLEASSTR(0x2000000000L),
    SPECIFIERFLAG_COMPONENT_OPTIONAL(0x4000000000L),
    SPECIFIERFLAG_COMPONENT_EXCLUDE(0x8000000000L),
    SPECIFIERFLAG_TYPEINFO_IGNORE(0x10000000000L),
    SPECIFIERFLAG_GPU(0x20000000000L);

    private final long value;

    SpecifierFlagGreatCircle(long value) {
        this.value = value;
    }

    public String shortName() {
        return name().substring("SPECIFIERFLAG_".length());
    }

    public static Set<SpecifierFlagGreatCircle> fromValue(long value) {
        Set<SpecifierFlagGreatCircle> flags = EnumSet.noneOf(SpecifierFlagGreatCircle.class);
        for (var flag : values()) {
            if ((value & flag.value) == flag.value) {
                flags.add(flag);
            }
        }
        return flags;
    }
}
