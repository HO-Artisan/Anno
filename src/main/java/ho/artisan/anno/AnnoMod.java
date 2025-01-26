package ho.artisan.anno;

import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.core.resolver.DataGenerationResolver;
import ho.artisan.anno.core.resolver.Resolver;
import ho.artisan.anno.resolver.EntryResolver;
import ho.artisan.anno.resolver.FuelResolver;
import ho.artisan.anno.resolver.SimpleModelResolver;
import ho.artisan.anno.resolver.lang.LangResolver;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class AnnoMod implements ModInitializer, AnnoEntrypoint {
    public static final String MOD_ID = "anno";
    public static final String MOD_NAME = "Anno";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        final List<AnnoEntrypoint> entrypoints = FabricLoader.getInstance().getEntrypoints(AnnoEntrypoint.KEY, AnnoEntrypoint.class);
        final List<Resolver> resolvers = new ArrayList<>();
        final List<Registration> registrations = new ArrayList<>();
        for (AnnoEntrypoint entrypoint : entrypoints) {
            entrypoint.addResolver(resolver -> {
                if (!(resolver instanceof DataGenerationResolver))
                    resolvers.add(resolver);
            });
            entrypoint.addRegistration(clazz -> registrations.add(Registration.wrap(clazz)));
        }
        Collections.sort(resolvers);
        Collections.sort(registrations);
        for (Resolver resolver : resolvers) {
            LOGGER.info("Resolver[{}] was loaded!", resolver.id());
        }
        for (Registration registration : registrations) {
            for (Entry entry : registration.entries()) {
                for (Resolver resolver : resolvers) {
                    if (resolver.match(entry)) {
                        resolver.before(registration);
                        resolver.process(entry, registration);
                        resolver.after(registration);
                    }
                }
            }
        }
    }

    @Override
    public void addResolver(Consumer<Resolver> consumer) {
        consumer.accept(new EntryResolver());
        consumer.accept(new FuelResolver());
        consumer.accept(new LangResolver());
        consumer.accept(new SimpleModelResolver());
    }

    @Override
    public void addRegistration(Consumer<Class<?>> consumer) {
    }
}
