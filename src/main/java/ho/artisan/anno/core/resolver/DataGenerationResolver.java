package ho.artisan.anno.core.resolver;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public interface DataGenerationResolver extends Resolver {
    void load(FabricDataGenerator generator);
}
