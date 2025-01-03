package ho.artisan.anno.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static ho.artisan.anno.AnnoMod.LOGGER;

public class FieldEntry extends Entry {
    private final Field field;
    private final Object instance;

    public FieldEntry(Field field, Object instance) {
        super(field, instance);
        this.field = field;
        this.field.setAccessible(true);
        this.instance = instance;
        if (Modifier.isFinal(field.getModifiers()))
            LOGGER.error("Field '{}' can not be modified with 'final'", field.getName());
    }

    public <V> void set(V value) {
        try {
            this.field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <V> void set(boolean value) {
        try {
            this.field.setBoolean(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <V> void set(byte value) {
        try {
            this.field.setByte(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <V> void set(double value) {
        try {
            this.field.setDouble(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <V> void set(float value) {
        try {
            this.field.setFloat(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <V> void set(int value) {
        try {
            this.field.setInt(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <V> void set(long value) {
        try {
            this.field.setLong(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <V> void set(short value) {
        try {
            this.field.setShort(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<FieldEntry> get(Class<? extends T> clazz, T instance) {
        List<FieldEntry> list = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            list.add(new FieldEntry(field, instance));
        }
        return list;
    }
}
