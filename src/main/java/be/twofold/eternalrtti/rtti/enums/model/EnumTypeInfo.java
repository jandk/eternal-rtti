package be.twofold.eternalrtti.rtti.enums.model;

import be.twofold.eternalrtti.rtti.*;

import java.util.*;

public record EnumTypeInfo(
    String name,
    Set<SpecifierFlag> flags,
    EnumType type,
    List<EnumValueInfo> values
) {
}
