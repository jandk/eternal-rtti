package be.twofold.eternalrtti.rtti.classes.model;

import be.twofold.eternalrtti.rtti.*;

import java.util.*;

public record ClassVariableInfo(
    String type,
    String ops,
    String name,
    int offset,
    int size,
    Set<SpecifierFlag> flags,
    String comment
) {
}
