package be.twofold.rtti.common.classes;

import java.util.*;

public record ClassTypeInfo(
    String name,
    String superType,
    int size,
    List<ClassVariableInfo> templateParms,
    List<ClassVariableInfo> variables,
    List<String> metaData,
    int nameHash
) {
    public ClassTypeInfo(String name, String superType, int size, List<ClassVariableInfo> templateParms, List<ClassVariableInfo> variables, List<String> metaData) {
        this(name, superType, size, templateParms, variables, metaData, 0);
    }
}
