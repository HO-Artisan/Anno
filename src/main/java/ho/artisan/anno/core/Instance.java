package ho.artisan.anno.core;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Registration是一个包装成员变量的操作单元。
 */
public final class Instance extends Anno {
    private final List<Field> fields;

    private Instance(Class<?> clazz) {
        super(clazz);
        fields = Arrays.asList(clazz.getDeclaredFields());
    }

    public List<Entry> entries() {
        return fields.stream().map((Entry::wrap)).toList();
    }

    public List<Entry> filter(Predicate<Entry> entryPredicate) {
        return fields.stream().map((Entry::wrap)).filter(entryPredicate).toList();
    }

    public static Instance wrap(Class<?> clazz) {
        return new Instance(clazz);
    }
}
