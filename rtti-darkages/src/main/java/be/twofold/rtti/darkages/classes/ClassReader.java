package be.twofold.rtti.darkages.classes;

import be.twofold.rtti.common.classes.*;
import be.twofold.rtti.common.utils.*;

import java.util.*;
import java.util.stream.*;

public final class ClassReader {
    private final PeWrapper pe;

    public ClassReader(PeWrapper pe) {
        this.pe = pe;
    }

    public List<ClassTypeInfo> read(long offset, int count) {
        var types = BufferUtils.readStructs(
            pe.getBuffer(offset),
            count - 1,
            ClassTypeInfoRaw::read
        );

        return types.stream()
            .map(this::map)
            .toList();
    }

    private ClassTypeInfo map(ClassTypeInfoRaw raw) {
        var name = pe.getCString(raw.name());
        var superType = pe.getCString(raw.superType());
        var templateParms = readVariables(raw.templateParms(), 0);
        var variables = readVariables(raw.variables(), raw.variableNameHashes());
        // var metaData = readMetaData(raw.metaData());

        return new ClassTypeInfo(name, superType, raw.size(), templateParms, variables, List.of(), raw.nameHash());
    }

    private List<ClassVariableInfo> readVariables(long pointer, long nameHashesPointer) {
        if (pointer == 0) {
            return List.of();
        }
        var buffer = pe.getBufferOptional(pointer);
        if (buffer.isEmpty()) {
            return List.of();
        }


        var variables = Stream.generate(() -> ClassVariableInfoRaw.read(buffer.get()))
            .takeWhile(raw -> raw.type() != 0)
            .toList();

        long[] nameHashes;
        if (nameHashesPointer != 0) {
            nameHashes = readVariableNameHashes(nameHashesPointer);
        } else {
            nameHashes = new long[variables.size()];
        }

        return IntStream.range(0, variables.size())
            .mapToObj(i -> {
                var variable = variables.get(i);
                var nameHash = nameHashes != null ? nameHashes[i] : 0;
                return mapVariable(variable, nameHash);
            })
            .toList();
    }

    private long[] readVariableNameHashes(long pointer) {
        if (pointer == 0) {
            return new long[0];
        }
        var buffer = pe.getBufferOptional(pointer);
        if (buffer.isEmpty()) {
            return new long[0];
        }

        return Stream.generate(() -> buffer.get().getLong())
            .mapToLong(Long::longValue)
            .takeWhile(l -> l != 0)
            .toArray();
    }

    private List<String> readMetaData(long pointer) {
        if (pointer == 0) {
            return List.of();
        }

        var buffer = pe.getBufferOptional(pointer);
        if (buffer.isEmpty()) {
            return List.of();
        }

        return Stream.generate(() -> pe.getCString(buffer.get().getLong()))
            .takeWhile(s -> !s.isEmpty())
            .toList();
    }

    private ClassVariableInfo mapVariable(ClassVariableInfoRaw raw, long nameHash) {
        var type = pe.getCString(raw.type());
        var ops = pe.getCString(raw.ops());
        var name = pe.getCString(raw.name());
        var flags = SpecifierFlagDarkAges.fromValue(raw.flags());
        var comment = pe.getCString(raw.comment());

        return new ClassVariableInfo(type, ops, name, raw.offset(), raw.size(), flags, comment, nameHash);
    }
}
