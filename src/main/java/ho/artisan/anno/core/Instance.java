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
    private final Object value;

    private Instance(Object object, Class<?> clazz) {
        super(clazz);
        this.value = object;
        fields = Arrays.asList(clazz.getDeclaredFields());
    }

    public List<Value> values() {
        return fields.stream().map(field -> Value.wrap(value, field)).toList();
    }

    public List<Value> filter(Predicate<Value> entryPredicate) {
        return fields.stream().map(field -> Value.wrap(value, field)).toList();
    }

    public static Instance wrap(Object object, Class<?> clazz) {
        return new Instance(object, clazz);
    }
}
