package ho.artisan.anno.core;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Registration是一个包装成员变量的操作单元。
 */
public final class Instance extends Anno implements Comparable<Instance> {
    private final List<Value> values;
    private final Object object;

    private Instance(Object object, Class<?> clazz) {
        super(clazz);
        this.object = object;
        values = Arrays.stream(clazz.getDeclaredFields()).map(field -> Value.wrap(this.object, field)).sorted().toList();
    }

    public List<Value> values() {
        return values;
    }

    public List<Value> filter(Predicate<Value> predicate) {
        List<Value> list = new ArrayList<>();
        for (Value value : values) {
            if (predicate.test(value))
                list.add(value);
        }
        return list;
    }

    public static Instance wrap(Object object, Class<?> clazz) {
        return new Instance(object, clazz);
    }

    @Override
    public int compareTo(@NotNull Instance instance) {
        return this.priority() - instance.priority();
    }
}
