package be.twofold.rtti.common.constants;

import java.util.*;

public final class ConstantWriter {
    private final StringBuilder builder = new StringBuilder();

    public ConstantWriter write(List<ConstantInfo> types) {
        types.forEach(this::writeConstant);
        return this;
    }

    private void writeConstant(ConstantInfo constantInfo) {
        builder.append("const ")
            .append(constantInfo.type()).append(' ')
            .append(constantInfo.name()).append(" = ")
            .append(constantInfo.value()).append(";\n");
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
