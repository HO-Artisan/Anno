package ho.artisan.anno.core.resolver;

import net.minecraft.data.DataGenerator;

public interface DataGenerationResolver extends Resolver {
    void load(DataGenerator generator);
}
