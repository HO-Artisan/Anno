package ho.artisan.anno;

import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.core.resolver.ClientResolver;
import ho.artisan.anno.core.resolver.DataGenerationResolver;
import ho.artisan.anno.core.resolver.Resolver;
import ho.artisan.anno.resolver.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class AnnoMod implements ModInitializer, AnnoEntrypoint, ClientModInitializer {
    public static final String MOD_ID = "anno";
    public static final String MOD_NAME = "Anno";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        final List<AnnoEntrypoint> entrypoints = FabricLoader.getInstance().getEntrypoints(AnnoEntrypoint.KEY, AnnoEntrypoint.class);
        final List<Resolver> resolvers = new ArrayList<>();
        final List<Registration> registrations = new ArrayList<>();
        for (AnnoEntrypoint entrypoint : entrypoints) {
            entrypoint.addResolver(resolvers::add);
            entrypoint.addRegistration(clazz -> registrations.add(Registration.wrap(clazz)));
        }
        Collections.sort(resolvers);
        Collections.sort(registrations);
        for (Resolver resolver : resolvers) {
            LOGGER.info("Resolver[{}] was loaded!", resolver.id());
        }
        for (Registration registration : registrations) {
            for (Resolver resolver : resolvers) {
                resolver.before(registration);
                for (Entry entry : registration.entries()) {
                    if (resolver.match(entry)) {
                        resolver.process(entry, registration);
                    }
                }
                resolver.after(registration);
            }
        }
    }

    @Override
    public void onInitializeClient() {
        final List<AnnoEntrypoint> entrypoints = FabricLoader.getInstance().getEntrypoints(AnnoEntrypoint.KEY, AnnoEntrypoint.class);
        final List<ClientResolver> resolvers = new ArrayList<>();
        final List<Registration> registrations = new ArrayList<>();
        for (AnnoEntrypoint entrypoint : entrypoints) {
            entrypoint.addClientResolver(resolvers::add);
            entrypoint.addRegistration(clazz -> registrations.add(Registration.wrap(clazz)));
        }
        Collections.sort(resolvers);
        Collections.sort(registrations);
        for (ClientResolver resolver : resolvers) {
            LOGGER.info("ClientResolver[{}] was loaded!", resolver.id());
        }
        for (Registration registration : registrations) {
            for (ClientResolver resolver : resolvers) {
                resolver.before(registration);
                for (Entry entry : registration.entries()) {
                    if (resolver.match(entry)) {
                        resolver.process(entry, registration);
                    }
                }
                resolver.after(registration);
            }
        }
    }

    @Override
    public void addResolver(Consumer<Resolver> consumer) {
        consumer.accept(new RegistryResolver());
        consumer.accept(new FuelResolver());
    }

    @Override
    public void addDataGenResolver(Consumer<DataGenerationResolver> consumer) {
        consumer.accept(new LangResolver());
        consumer.accept(new SimpleModelResolver());
        consumer.accept(new SimpleLootTableResolver());
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void addClientResolver(Consumer<ClientResolver> consumer) {
        consumer.accept(new TipResolver());
    }

    @Override
    public void addRegistration(Consumer<Class<?>> consumer) {}
}
