package be.twofold.rtti.common.classes;

import be.twofold.rtti.common.*;

import java.util.*;

public record ClassVariableInfo(
    String type,
    String ops,
    String name,
    int offset,
    int size,
    Set<? extends SpecifierFlag> flags,
    String comment
) {
}
