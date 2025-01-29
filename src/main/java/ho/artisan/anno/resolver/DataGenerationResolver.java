package ho.artisan.anno.resolver;

import ho.artisan.anno.core.Registration;
import ho.artisan.anno.core.resolver.Resolver;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public interface DataGenerationResolver extends Resolver {
    void load(FabricDataGenerator generator, Registration registration);
}
