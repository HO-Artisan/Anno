package ho.artisan.anno.datagen;

import ho.artisan.anno.AnnoEntrypoint;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.resolver.DataGenerationResolver;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ho.artisan.anno.AnnoMod.LOGGER;

public final class AnnoDataGenerationEntrypoint implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        final List<AnnoEntrypoint> entrypoints = FabricLoader.getInstance().getEntrypoints(AnnoEntrypoint.KEY, AnnoEntrypoint.class);
        final List<DataGenerationResolver> resolvers = new ArrayList<>();
        final List<Registration> registrations = new ArrayList<>();
        for (AnnoEntrypoint entrypoint : entrypoints) {
            entrypoint.addDataGenResolver(resolvers::add);
            entrypoint.addRegistration(clazz -> registrations.add(Registration.wrap(clazz)));
        }
        Collections.sort(resolvers);
        Collections.sort(registrations);
        for (DataGenerationResolver resolver : resolvers) {
            LOGGER.info("DataGenerationResolver[{}] was loaded!", resolver.id());
        }
        for (Registration registration : registrations) {
            for (DataGenerationResolver resolver : resolvers) {
                resolver.before(registration);
                for (Entry entry : registration.entries()) {
                    if (resolver.match(entry)) {
                        resolver.process(entry, registration);
                    }
                }
                resolver.after(registration);
                resolver.load(generator, registration);
            }
        }
    }
}
