package ho.artisan.anno;

import ho.artisan.anno.core.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;

import static ho.artisan.anno.AnnoMod.LOGGER;

public class AnnoUtil {
    public static final String RESOLVER_ENTRYPOINT = "ho-resolver";
    public static final String DATAGEN_RESOLVER_ENTRYPOINT = "ho-datagen-resolver";
    public static final String CLIENT_RESOLVER_ENTRYPOINT = "ho-client-resolver";
    public static final String REGISTRATION_ENTRYPOINT = "ho-registration";

    public static <T extends Annotation, A> A getValue(T target, Class<A> aClass) {
        try {
            Method method = target.getClass().getMethod("value");
            method.setAccessible(true);
            return aClass.cast(method.invoke(target));
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public static void load() {
        FabricLoader loader = FabricLoader.getInstance();
        List<Resolver> resolvers = loader.getEntrypoints(RESOLVER_ENTRYPOINT, Resolver.class).stream()
                .sorted(Comparator.comparingInt(o -> o.toAnno().priority()))
                .toList();
        List<Registration> registrations = loader.getEntrypoints(REGISTRATION_ENTRYPOINT, Object.class).stream()
                .map(o -> new Registration(o.getClass()))
                .sorted(Comparator.comparingInt(Anno::priority))
                .toList();
        for (Registration registration : registrations) {
            for (Resolver resolver : resolvers) {
                List<Entry> entries = registration.getEntries().stream()
                        .sorted(Comparator.comparingInt(Anno::priority))
                        .toList();
                for (Entry entry : entries) {
                    if (resolver.isSuitable(entry)) {
                        resolver.process(entry, registration);
                    }
                }
                LOGGER.info("Resolver:{} finished loading in {}", resolver.toAnno().id(), registration);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    @SuppressWarnings("rawtypes")
    public static void clientLoad() {
        FabricLoader loader = FabricLoader.getInstance();
        List<ClientResolver> resolvers = loader.getEntrypoints(CLIENT_RESOLVER_ENTRYPOINT, ClientResolver.class).stream()
                .sorted(Comparator.comparingInt(o -> o.toAnno().priority()))
                .toList();
        List<Registration> registrations = loader.getEntrypoints(REGISTRATION_ENTRYPOINT, Object.class).stream()
                .map(o -> new Registration(o.getClass()))
                .sorted(Comparator.comparingInt(Anno::priority))
                .toList();
        for (Registration registration : registrations) {
            for (ClientResolver resolver : resolvers) {
                List<Entry> entries = registration.getEntries().stream()
                        .sorted(Comparator.comparingInt(Anno::priority))
                        .toList();
                for (Entry entry : entries) {
                    if (resolver.isSuitable(entry)) {
                        resolver.process(entry, registration);
                    }
                }
                LOGGER.info("ClientResolver:{} finished loading in {}", resolver.toAnno().id(), registration);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static void datagenLoad(FabricDataGenerator generator) {
        String id = generator.getModId();
        FabricLoader loader = FabricLoader.getInstance();
        List<DataGenResolver> resolvers = loader.getEntrypoints(DATAGEN_RESOLVER_ENTRYPOINT, DataGenResolver.class).stream()
                .sorted(Comparator.comparingInt(o -> o.toAnno().priority()))
                .toList();
        List<Registration> registrations = loader.getEntrypoints(REGISTRATION_ENTRYPOINT, Object.class).stream()
                .map(o -> new Registration(o.getClass()))
                .filter(registration -> registration.id().equals(id))
                .sorted(Comparator.comparingInt(Anno::priority))
                .toList();
        for (Registration registration : registrations) {
            for (DataGenResolver resolver : resolvers) {
                List<Entry> entries = registration.getEntries().stream()
                        .sorted(Comparator.comparingInt(Anno::priority))
                        .toList();
                for (Entry entry : entries) {
                    if (resolver.isSuitable(entry)) {
                        resolver.process(entry, registration);
                        resolver.finish(generator, registration);
                    }
                }
                LOGGER.info("DataGenResolver:{} finished loading in {}", resolver.toAnno().id(), registration);
            }
        }
    }
}
