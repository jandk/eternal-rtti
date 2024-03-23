package be.twofold.eternalrtti.rtti.enums.model;

import be.twofold.eternalrtti.rtti.base.*;

import java.util.*;

public record EnumTypeInfo(
    String name,
    Set<SpecifierFlagEternal> flags,
    EnumType type,
    List<EnumValueInfo> values
) {
}
