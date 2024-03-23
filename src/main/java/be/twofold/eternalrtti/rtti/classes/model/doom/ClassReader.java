package be.twofold.eternalrtti.rtti.classes.model.doom;

import be.twofold.eternalrtti.rtti.base.*;
import be.twofold.eternalrtti.rtti.classes.model.*;
import be.twofold.eternalrtti.utils.*;

import java.util.*;
import java.util.stream.*;

public final class ClassReader {
    private final PeWrapper pe;

    public ClassReader(PeWrapper pe) {
        this.pe = pe;
    }

    public List<ClassTypeInfo> read(int offset, int count) {
        var types = BufferUtils.readStructs(
            pe.getDataBuffer(offset),
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
        var templateParms = readVariables(raw.templateParms());
        var variables = readVariables(raw.variables());
        var metaData = readMetaData(raw.metaData());

        return new ClassTypeInfo(name, superType, raw.size(), templateParms, variables, metaData);
    }

    private List<ClassVariableInfo> readVariables(long pointer) {
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

        return variables.stream()
            .map(this::mapVariable)
            .toList();
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

    private ClassVariableInfo mapVariable(ClassVariableInfoRaw raw) {
        var type = pe.getCString(raw.type());
        var ops = pe.getCString(raw.ops());
        var name = pe.getCString(raw.name());
        var flags = SpecifierFlagDoom.fromValue(raw.flags());
        var comment = pe.getCString(raw.comment());

        return new ClassVariableInfo(type, ops, name, raw.offset(), raw.size(), flags, comment);
    }
}
