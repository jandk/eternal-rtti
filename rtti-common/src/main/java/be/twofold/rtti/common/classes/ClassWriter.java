package be.twofold.rtti.common.classes;

import be.twofold.rtti.common.*;

import java.util.*;
import java.util.stream.*;

public final class ClassWriter {
    private static final HexFormat HEX_FORMAT = HexFormat.of();

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
            if (type.nameHash() != 0) {
                builder.append(", Hash: 0x").append(HEX_FORMAT.toHexDigits(type.nameHash()));
            }
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
            if (variable.nameHash() != 0) {
                builder.append(", Hash: 0x").append(HEX_FORMAT.toHexDigits(variable.nameHash()));
            }
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
        String ops = variable.ops();
        String arr = "";
        if (ops.matches("\\[\\d+]")) {
            arr = ops;
            ops = "";
        }
        builder.append("    ")
            .append(variable.type()).append(ops).append(' ')
            .append(variable.name()).append(arr).append(";\n");
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
