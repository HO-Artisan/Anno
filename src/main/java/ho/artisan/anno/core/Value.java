package ho.artisan.anno.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Entry 是一个包装成员字段的操作单元。
 */
public final class Value extends Anno {
    private final Object value;

    private Value(Object instance, Field field) throws IllegalAccessException {
        super(field);
        field.setAccessible(true);
        value = field.get(instance);
    }

    public <T> T cast(Class<T> tClass) {
        return tClass.cast(value);
    }

    public <T> boolean is(Class<T> tClass) {
        return tClass.isInstance(value);
    }

    public static Value wrap(Object instance, Field field) {
        try {
            if (Modifier.isStatic(field.getModifiers()))
                throw new RuntimeException(field + " is static! Please use " + Entry.class);
            return new Value(instance, field);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
