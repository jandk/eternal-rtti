package be.twofold.rtti.common.classes;

import java.util.*;

public record ClassTypeInfo(
    String name,
    String superType,
    int size,
    List<ClassVariableInfo> templateParms,
    List<ClassVariableInfo> variables,
    List<String> metaData
) {
}
