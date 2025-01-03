package ho.artisan.anno.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static ho.artisan.anno.AnnoMod.LOGGER;

/**
 * Entry is a class containing the specific value and annotations.
 */
public class Entry extends Anno {
    private final Object object;

    /**
     * The construction method for member variable.
     */
    public Entry(Field field, Object instance) {
        super(field);
        field.setAccessible(true);
        try {
            object = field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The construction method for static variable
     */
    public Entry(Field field) {
        this(field, null);
        if (!Modifier.isStatic(field.getModifiers()))
            LOGGER.error("Field '{}' is not modified with 'static'", field.getName());
    }

    /**
     * Cast this Entry`s value to a designated type.
     * If it meets any exceptions, it will return null.
     */
    public <T> T cast(Class<T> tClass) {
        try {
            return tClass.cast(object);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Whether this Entry is a designated type.
     */
    public <T> boolean is(Class<T> tClass) {
        return tClass.isInstance(object);
    }

    @Override
    public String toString() {
        return "Entry:" + id();
    }
}
