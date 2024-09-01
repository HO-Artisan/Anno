package ho.artisan.anno.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static ho.artisan.anno.AnnoMod.LOGGER;

public class Entry extends Anno {
    private final Object object;

    public Entry(Field field, Object instance) {
        super(field);
        field.setAccessible(true);
        try {
            object = field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Entry(Field field) {
        this(field, null);
        if (!Modifier.isStatic(field.getModifiers()))
            LOGGER.error("Field '{}' is not modified with 'static'", field.getName());
    }

    public <T> T cast(Class<T> tClass) {
        try {
            return tClass.cast(object);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> boolean is(Class<T> tClass) {
        return tClass.isInstance(object);
    }

    @Override
    public String toString() {
        return "Entry:" + id();
    }
}
