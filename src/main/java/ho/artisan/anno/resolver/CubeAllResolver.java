package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.ID;
import ho.artisan.anno.annotation.datagen.model.CubeAll;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.EntryContainer;
import net.minecraft.block.Block;

@ID("cube_all")
public class CubeAllResolver extends ModelResolver<CubeAll> {
    @Override
    public void process(Entry entry, EntryContainer registration) {
        if (entry.is(Block.class)) {
            Block block = entry.cast(Block.class);
            blocks.add(generator -> generator.registerSimpleCubeAll(block));
        }
    }

    @Override
    public Class<CubeAll> aClass() {
        return CubeAll.class;
    }
}
