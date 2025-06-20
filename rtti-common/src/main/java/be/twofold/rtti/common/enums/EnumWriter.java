package be.twofold.rtti.common.enums;

import be.twofold.rtti.common.*;

import java.util.*;
import java.util.stream.*;

public final class EnumWriter {
    private static final HexFormat HEX_FORMAT = HexFormat.of().withUpperCase();
    private final StringBuilder builder = new StringBuilder();

    public EnumWriter write(List<EnumTypeInfo> types) {
        types.forEach(this::writeEnum);
        return this;
    }

    private void writeEnum(EnumTypeInfo type) {
        if (!type.flags().isEmpty()) {
            var flags = type.flags().stream()
                .map(SpecifierFlag::shortName)
                .collect(Collectors.joining("|"));
            builder.append("// ").append(flags).append('\n');
        }
        if (type.nameHash() != 0) {
            builder
                .append("// Hash: 0x")
                .append(HEX_FORMAT.toHexDigits(type.nameHash()))
                .append('\n');
        }
        builder.append("enum ")
            .append(type.name()).append(" : ")
            .append(type.type().getType()).append(" {\n");

        type.values().forEach(this::writeEnumValue);

        builder.append("};\n\n");
    }

    private void writeEnumValue(EnumValueInfo value) {
        if (value.comment() != null && !value.comment().isEmpty()) {
            builder.append("    // ").append(value.comment()).append('\n');
        }

        builder.append("    ")
            .append(value.name()).append(" = ")
            .append(value.value()).append(',');

        if (value.nameHash() != 0) {
            builder
                .append(" // Hash: 0x")
                .append(HEX_FORMAT.toHexDigits(value.nameHash()));
        }

        builder.append('\n');
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
