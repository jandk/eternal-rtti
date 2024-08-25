package be.twofold.rtti.common.utils;

import java.lang.reflect.*;
import java.util.*;

public final class CsvUtils {
    public static <T> String toCsv(List<T> values, Class<T> clazz) {
        var builder = new StringBuilder();

        var components = clazz.getRecordComponents();
        for (var component : components) {
            builder.append(component.getName()).append(',');
        }
        builder.setLength(builder.length() - 1);
        builder.append('\n');

        for (var value : values) {
            for (var component : components) {
                var o = getRecordComponent(value, component);
                var s = switch (o) {
                    case List<?> $ -> "";
                    case byte[] array -> Arrays.toString(array);
                    case short[] array -> Arrays.toString(array);
                    case int[] array -> Arrays.toString(array);
                    case int[][] array -> Arrays.deepToString(array);
                    case float[] array -> Arrays.toString(array);
                    default -> Objects.toString(o);
                };
                if (s.contains(",")) {
                    builder.append('"').append(s).append('"');
                } else {
                    builder.append(s);
                }
                builder.append(',');
            }
            builder.setLength(builder.length() - 1);
            builder.append('\n');
        }
        return builder.toString();
    }

    private static Object getRecordComponent(Object o, RecordComponent component) {
        try {
            return component.getAccessor().invoke(o);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
