package ho.artisan.anno.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Registration extends Anno {
    private final List<Entry> entries = new ArrayList<>();

    public Registration(Class<?> clazz) {
        super(clazz);
        for (Field field : clazz.getFields()) {
            entries.add(new Entry(field));
        }
    }

    public List<Entry> getEntries() {
        return List.copyOf(entries);
    }

    @Override
    public String toString() {
        return "Registration:" + id();
    }
}
