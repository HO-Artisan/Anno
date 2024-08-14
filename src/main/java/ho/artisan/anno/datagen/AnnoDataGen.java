package ho.artisan.anno.datagen;

import ho.artisan.anno.example.AnnoRegistration;
import ho.artisan.anno.AnnoResolvers;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;

public class AnnoDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {

        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            AnnoResolvers.dataGen(generator, generator.createPack(), AnnoRegistration.class);
        }
    }
}
