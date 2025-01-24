package ho.artisan.anno.core.resolver;

import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import org.jetbrains.annotations.NotNull;

public interface Resolver extends Comparable<Resolver> {
    boolean match(Entry entry);

    void process(Entry entry, Registration registration);

    String id();

    default void before(Registration registration) {}

    default void after(Registration registration) {}

    default int priority() {
        return 0;
    }

    @Override
    default int compareTo(@NotNull Resolver resolver) {
        return this.priority() - resolver.priority();
    }
}
