package ho.artisan.anno.core;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Entry 是一个包装静态字段的操作单元。
 */
public final class Entry extends Anno implements Comparable<Entry> {
    private final Object value;

    private Entry(Field field) throws IllegalAccessException {
        super(field);
        field.setAccessible(true);
        value = field.get(null);
    }

    /**
     * 将Entry转换成某一具体类型。
     */
    public <T> T cast(Class<T> tClass) {
        return tClass.cast(value);
    }

    /**
     * 判断Entry是否为某一具体类型。
     */
    public <T> boolean is(Class<T> tClass) {
        return tClass.isInstance(value);
    }

    public static Entry wrap(Field field) {
        try {
            if (!Modifier.isStatic(field.getModifiers()))
                throw new RuntimeException(field + " is not static! Please use " + Value.class);
            return new Entry(field);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(@NotNull Entry entry) {
        return this.priority() - entry.priority();
    }
}
