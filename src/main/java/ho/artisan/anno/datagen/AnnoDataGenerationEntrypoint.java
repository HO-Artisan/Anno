package ho.artisan.anno.datagen;

import ho.artisan.anno.AnnoEntrypoint;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.core.resolver.DataGenerationResolver;
import ho.artisan.anno.core.resolver.Resolver;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnnoDataGenerationEntrypoint implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        final List<AnnoEntrypoint> entrypoints = FabricLoader.getInstance().getEntrypoints("anno-entrypoint", AnnoEntrypoint.class);
        final List<Resolver> resolvers = new ArrayList<>();
        final List<Registration> registrations = new ArrayList<>();
        for (AnnoEntrypoint entrypoint : entrypoints) {
            entrypoint.addResolver(resolvers::add);
            entrypoint.addRegistration(clazz -> registrations.add(Registration.wrap(clazz)));
        }
        Collections.sort(resolvers);
        Collections.sort(registrations);
        for (Registration registration : registrations) {
            for (Entry entry : registration.entries()) {
                for (Resolver resolver : resolvers) {
                    if (resolver instanceof DataGenerationResolver dataGenerationResolver)
                        dataGenerationResolver.load(generator);
                }
            }
        }
    }
}
