package be.twofold.eternalrtti.rtti.enums;

import be.twofold.eternalrtti.rtti.*;
import be.twofold.eternalrtti.rtti.enums.model.*;

import java.util.*;
import java.util.stream.*;

public final class EnumWriter {
    private final StringBuilder builder = new StringBuilder();

    public void write(List<EnumTypeInfo> types) {
        types.forEach(this::writeEnum);
    }

    private void writeEnum(EnumTypeInfo type) {
        if(!type.flags().isEmpty()){
            var flags = type.flags().stream()
                .map(SpecifierFlag::shortName)
                .collect(Collectors.joining("|"));
            builder.append("// ").append(flags).append('\n');
        }
        builder.append("enum ")
            .append(type.name()).append(" : ")
            .append(type.type().getType()).append(" {\n");

        type.values().forEach(this::writeEnumValue);

        builder.append("};\n\n");
    }

    private void writeEnumValue(EnumValueInfo value) {
        builder.append("    ")
            .append(value.name()).append(" = ")
            .append(value.value()).append(",\n");
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
