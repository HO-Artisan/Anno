package ho.artisan.anno.core.resolver.datagen.model.block;

import ho.artisan.anno.core.annotation.datagen.model.CubeAll;
import ho.artisan.anno.core.resolver.datagen.model.ModelResolver;
import net.minecraft.block.Block;

public class CubeAllResolver extends ModelResolver<CubeAll> {
    @Override
    public <T> void process(Target<T> target, Class<?> registration) {
        Block block = (Block) target.object();
        blockModel(generator -> generator.registerSimpleCubeAll(block));
    }

    @Override
    public Class<CubeAll> annoClass() {
        return CubeAll.class;
    }
}
