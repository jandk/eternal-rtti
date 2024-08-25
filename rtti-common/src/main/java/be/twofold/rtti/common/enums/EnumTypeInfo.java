package be.twofold.rtti.common.enums;

import be.twofold.rtti.common.*;

import java.util.*;

public record EnumTypeInfo(
    String name,
    Set<SpecifierFlag> flags,
    EnumType type,
    List<EnumValueInfo> values
) {
}
