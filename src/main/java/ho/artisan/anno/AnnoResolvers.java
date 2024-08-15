package ho.artisan.anno;

import ho.artisan.anno.core.annotation.ID;
import ho.artisan.anno.core.resolver.DataGenResolver;
import ho.artisan.anno.core.resolver.Resolver;
import ho.artisan.anno.core.resolver.datagen.LangResolver;
import ho.artisan.anno.core.resolver.datagen.loot.DropSelfResolver;
import ho.artisan.anno.core.resolver.datagen.model.block.CubeAllResolver;
import ho.artisan.anno.core.resolver.datagen.model.item.GeneratedResolver;
import ho.artisan.anno.core.resolver.datagen.model.item.HandheldResolver;
import ho.artisan.anno.core.resolver.datagen.model.item.ParentedResolver;
import ho.artisan.anno.core.resolver.vanilla.FuelResolver;
import ho.artisan.anno.core.resolver.vanilla.RegResolver;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import static ho.artisan.anno.Anno.id;

public class AnnoResolvers {
    private static final ConcurrentHashMap<Identifier, Resolver<?>> RESOLVERS = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Identifier, DataGenResolver<?>> DATAGEN_RESOLVERS = new ConcurrentHashMap<>();

    static {
        //Registry
        register(id("registry"), new RegResolver());

        //Datagen
        //Lang
        register(id("language"), new LangResolver());
        //Model
        //Item
        register(id("handheld"), new HandheldResolver());
        register(id("generated"), new GeneratedResolver());
        //Block
        register(id("cube_all"), new CubeAllResolver());
        register(id("parented"), new ParentedResolver());
        //LootTable
        register(id("drop_self"), new DropSelfResolver());

        //Fuel
        register(id("fuel"), new FuelResolver());
    }

    @SuppressWarnings("UnusedReturnValue")
    public static synchronized <A extends Annotation> Resolver<A> register(Identifier id, Resolver<A> resolver) {
        if (resolver instanceof DataGenResolver<A> dataGenResolver)
            DATAGEN_RESOLVERS.put(id, dataGenResolver);
        else
            RESOLVERS.put(id, resolver);
        return resolver;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static <A extends Annotation> Resolver<A> get(Identifier id, Class<A> annoClass) {
        if (RESOLVERS.containsKey(id)) {
            Resolver<?> resolver = RESOLVERS.get(id);
            if (resolver.annoClass() == annoClass)
                return (Resolver<A>) resolver;
        }
        return null;
    }

    public static void dataGen(FabricDataGenerator generator, FabricDataGenerator.Pack pack, Class<?> registration) {
        dataGen(generator, pack, registration, identifier -> true);
    }

    public static void dataGen(FabricDataGenerator generator, FabricDataGenerator.Pack pack, Class<?> registration, Predicate<Identifier> filter) {
        if (registration.isAnnotationPresent(ID.class)) {
            List<Field> fields = List.of(registration.getFields());
            for (Map.Entry<Identifier, DataGenResolver<?>> entry : DATAGEN_RESOLVERS.entrySet()) {
                Identifier id = entry.getKey();
                DataGenResolver<?> resolver = entry.getValue();
                if (filter.test(id)) {
                    fields.stream()
                            .filter(field -> field.isAnnotationPresent(ID.class))
                            .filter(resolver::condition)
                            .map(resolver::wrap)
                            .filter(Objects::nonNull)
                            .forEach(target -> resolver.process(target, registration));
                    resolver.apply(generator, pack, registration);
                }
            }
        }else {
            throw new RuntimeException(registration + " must be annotated by " + ID.class);
        }
    }

    public static void resolve(Class<?> registration) {
        resolve(registration, identifier -> true);
    }

    public static void resolve(Class<?> registration, Predicate<Identifier> filter) {
        if (registration.isAnnotationPresent(ID.class)) {
            List<Field> fields = List.of(registration.getFields());
            for (Map.Entry<Identifier, Resolver<?>> entry : RESOLVERS.entrySet()) {
                Identifier id = entry.getKey();
                Resolver<?> resolver = entry.getValue();
                if (filter.test(id)) {
                    fields.stream()
                            .filter(field -> field.isAnnotationPresent(ID.class))
                            .filter(resolver::condition)
                            .map(resolver::wrap)
                            .filter(Objects::nonNull)
                            .forEach(target -> resolver.process(target, registration));
                }
            }
        }else {
            throw new RuntimeException(registration + " must be annotated by " + ID.class);
        }
    }
}
