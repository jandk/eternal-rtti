package be.twofold.eternalrtti.rtti.typedefs;

import be.twofold.eternalrtti.rtti.typedefs.model.*;

import java.util.*;

public final class TypedefWriter {
    private final StringBuilder builder = new StringBuilder();

    public TypedefWriter write(List<TypedefInfo> types) {
        types.forEach(this::writeTypedef);
        return this;
    }

    private void writeTypedef(TypedefInfo type) {
        builder.append("typedef ")
            .append(type.type()).append(" ")
            .append(type.name()).append(";\n");
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
