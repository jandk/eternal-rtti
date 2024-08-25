package be.twofold.rtti.common.utils;

import java.nio.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public final class BufferUtils {
    private BufferUtils() {
    }

    public static <T> List<T> readStructs(ByteBuffer buffer, int count, Function<ByteBuffer, T> reader) {
        return IntStream.range(0, count)
            .mapToObj(i -> reader.apply(buffer))
            .toList();
    }
}
