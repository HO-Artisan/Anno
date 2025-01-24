package ho.artisan.anno.core;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Registration是一个包装静态工具类的操作单元。
 */
public final class Registration extends Anno implements Comparable<Registration> {
    private final List<Entry> entries;

    private Registration(Class<?> clazz) {
        super(clazz);
        entries = Arrays.stream(clazz.getDeclaredFields()).map((Entry::wrap)).sorted().toList();
    }

    public List<Entry> entries() {
        return entries;
    }

    public List<Entry> filter(Predicate<Entry> predicate) {
        List<Entry> list = new ArrayList<>();
        for (Entry entry : entries) {
            if (predicate.test(entry))
                list.add(entry);
        }
        return list;
    }

    public static Registration wrap(Class<?> clazz) {
        return new Registration(clazz);
    }

    @Override
    public int compareTo(@NotNull Registration registration) {
        return this.priority() - registration.priority();
    }
}
