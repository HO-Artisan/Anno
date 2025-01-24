package ho.artisan.anno;

import ho.artisan.anno.core.resolver.Resolver;

import java.util.function.Consumer;

public interface AnnoEntrypoint {
    void addResolver(Consumer<Resolver> consumer);

    void addRegistration(Consumer<Class<?>> consumer);
}
