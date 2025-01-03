package ho.artisan.anno.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Instance is a functional class to wrap an instance, and it is targeted to be used in instance interior.
 * @see Registration The Registration class to wrap a class for static members.
 */
public class Instance extends Anno implements EntryContainer {
    private final String name;
    private final List<Entry> entries = new ArrayList<>();

    public <T> Instance(Class<? extends T> clazz, T instance) {
        super(clazz);
        this.name = clazz.getName();
        for (Field field : clazz.getDeclaredFields()) {
            entries.add(new Entry(field, instance));
        }
    }

    @Override
    public List<Entry> getEntries() {
        return List.copyOf(entries);
    }

    @Override
    public String toString() {
        return "Instance"+ "(" + name + ")" +":" + id();
    }
}
