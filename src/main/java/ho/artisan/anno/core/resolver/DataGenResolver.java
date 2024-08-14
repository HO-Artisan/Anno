package ho.artisan.anno.core.resolver;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import java.lang.annotation.Annotation;
public interface DataGenResolver<A extends Annotation> extends Resolver<A> {
    void apply(FabricDataGenerator generator, FabricDataGenerator.Pack pack);
}
