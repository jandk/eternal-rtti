package be.twofold.eternalrtti.rtti.classes;

import be.twofold.eternalrtti.rtti.base.*;
import be.twofold.eternalrtti.rtti.classes.model.*;

import java.util.*;
import java.util.stream.*;

public final class ClassWriter {
    private final StringBuilder builder = new StringBuilder();

    public ClassWriter write(List<ClassTypeInfo> types) {
        types.forEach(this::writeClass);
        return this;
    }

    private void writeClass(ClassTypeInfo type) {
        if (!type.metaData().isEmpty()) {
            builder.append("// Metadata: \n");
            for (String metaDatum : type.metaData()) {
                builder.append("//  ").append(metaDatum).append('\n');
            }
        }
        if (type.size() != -1) {
            builder.append("// Size: ");
            appendHex(type.size());
            builder.append('\n');
        }
        builder.append("struct __cppobj ").append(type.name());
        if (!type.superType().isEmpty()) {
            builder.append(" : ").append(type.superType());
        }

        builder.append(" {\n");

        type.variables().forEach(this::writeVariable);

        builder.append("};\n\n");
    }

    private void writeVariable(ClassVariableInfo variable) {
        builder.append("    // ").append("Offset: ");
        appendHex(variable.offset());
        if (variable.size() != -1) {
            builder.append(", Size: ");
            appendHex(variable.size());
        }
        builder.append('\n');
        if (!variable.flags().isEmpty()) {
            var flags = variable.flags().stream()
                .map(SpecifierFlag::shortName)
                .collect(Collectors.joining("|"));
            builder.append("    // ").append(flags).append("\n");
        }
        if (!variable.comment().isEmpty()) {
            builder.append("    // ").append(variable.comment()).append('\n');
        }
        builder.append("    ")
            .append(variable.type()).append(variable.ops()).append(' ')
            .append(variable.name()).append(";\n");
    }

    private void appendHex(int n) {
        builder
            .append(n)
            .append(" (0x")
            .append(Integer.toHexString(n))
            .append(')');
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
