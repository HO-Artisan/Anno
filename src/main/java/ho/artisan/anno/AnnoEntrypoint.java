package ho.artisan.anno;

import ho.artisan.anno.core.resolver.ClientResolver;
import ho.artisan.anno.core.resolver.DataGenerationResolver;
import ho.artisan.anno.core.resolver.Resolver;

import java.util.function.Consumer;

public interface AnnoEntrypoint {
    String KEY = "anno-entrypoint";

    void addResolver(Consumer<Resolver> consumer);

    void addDataGenResolver(Consumer<DataGenerationResolver> consumer);

    void addClientResolver(Consumer<ClientResolver> consumer);

    void addRegistration(Consumer<Class<?>> consumer);
}
