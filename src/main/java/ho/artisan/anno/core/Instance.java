package ho.artisan.anno.core;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Registration是一个包装成员变量的操作单元。
 */
public final class Instance extends Anno implements Comparable<Instance> {
    private final List<Value> values;
    private final List<Handler> handlers;
    private final Object object;

    private Instance(Object object, Class<?> clazz) {
        super(clazz);
        this.object = object;
        this.handlers = Arrays.stream(clazz.getDeclaredMethods()).map(method -> Handler.wrap(this.object, method)).sorted().toList();
        this.values = Arrays.stream(clazz.getDeclaredFields()).map(field -> Value.wrap(this.object, field)).sorted().toList();
    }

    public List<Value> values() {
        return values;
    }

    public List<Handler> handlers() {
        return handlers;
    }

    public static Instance wrap(Object object, Class<?> clazz) {
        return new Instance(object, clazz);
    }

    @Override
    public int compareTo(@NotNull Instance instance) {
        return this.priority() - instance.priority();
    }
}
