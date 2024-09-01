package ho.artisan.anno.core;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import java.lang.annotation.Annotation;

public interface DataGenResolver<A extends Annotation> extends Resolver<A> {
    void finish(FabricDataGenerator generator, Registration registration);
}
