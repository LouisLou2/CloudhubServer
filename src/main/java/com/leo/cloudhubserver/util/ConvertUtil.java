package com.leo.cloudhubserver.util;

import java.lang.reflect.*;
import java.util.*;

public final class ConvertUtil{

    public static Map<String, String> deepToMap(Object bean) {
        Map<String, String> map = new LinkedHashMap<>();
        try {
            putValues(bean, map, null);
        } catch (IllegalAccessException x) {
            throw new IllegalArgumentException(x);
        }
        return map;
    }

    private static void putValues(Object bean,
                                  Map<String, String> map,
                                  String prefix)
            throws IllegalAccessException {
        Class<?> cls = bean.getClass();

        for (Field field : cls.getDeclaredFields()) {
            if (field.isSynthetic() || Modifier.isStatic(field.getModifiers()))
                continue;
            field.setAccessible(true);

            Object value = field.get(bean);
            String key;
            if (prefix == null) {
                key = field.getName();
            } else {
                key = prefix + "." + field.getName();
            }

            if (isValue(value)) {
                map.put(key, String.valueOf(value));
            } else {
                putValues(value, map, key);
            }
        }
    }

    private static final Set<Class<?>> VALUE_CLASSES =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    Object.class,    String.class, Boolean.class,
                    Character.class, Byte.class,   Short.class,
                    Integer.class,   Long.class,   Float.class,
                    Double.class
                    // etc.
            )));

    private static boolean isValue(Object value) {
        return value == null
                || value instanceof Enum<?>
                || VALUE_CLASSES.contains(value.getClass());
    }
}