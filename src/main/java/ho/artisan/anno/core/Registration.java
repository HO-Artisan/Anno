package ho.artisan.anno.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Registration is to warp a class containing static members for the Anno Mod`s using.
 * It will save all its static members to a list when initialization.
 */
public class Registration extends Anno implements EntryContainer {
    private final List<Entry> entries = new ArrayList<>();

    public Registration(Class<?> clazz) {
        super(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            entries.add(new Entry(field));
        }
    }

    @Override
    public List<Entry> getEntries() {
        return List.copyOf(entries);
    }

    @Override
    public String toString() {
        return "Registration:" + id();
    }
}
