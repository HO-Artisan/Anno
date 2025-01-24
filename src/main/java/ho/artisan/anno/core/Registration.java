package ho.artisan.anno.core;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Registration是一个包装静态工具类的操作单元。
 */
public final class Registration extends Anno {
    private final List<Field> fields;

    private Registration(Class<?> clazz) {
        super(clazz);
        fields = Arrays.asList(clazz.getDeclaredFields());
    }

    public List<Entry> entries() {
        return fields.stream().map((Entry::wrap)).toList();
    }

    public List<Entry> filter(Predicate<Entry> entryPredicate) {
        return fields.stream().map((Entry::wrap)).filter(entryPredicate).toList();
    }

    public static Registration wrap(Class<?> clazz) {
        return new Registration(clazz);
    }
}
