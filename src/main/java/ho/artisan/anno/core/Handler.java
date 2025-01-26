package ho.artisan.anno.core;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Handler extends Anno implements Comparable<Handler> {
    private final Object value;
    private final Method method;

    private Handler(Object instance, Method method) {
        super(method);
        method.setAccessible(true);
        this.method = method;
        this.value = instance;
    }

    public void invoke(Object... args) {
        try {
            method.invoke(value, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Handler wrap(Object instance, Method method) {
        return new Handler(instance, method);
    }

    @Override
    public int compareTo(@NotNull Handler entry) {
        return this.priority() - entry.priority();
    }
}
