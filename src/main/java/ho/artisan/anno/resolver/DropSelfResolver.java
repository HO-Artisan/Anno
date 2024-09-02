package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.ID;
import ho.artisan.anno.annotation.datagen.DropSelf;
import ho.artisan.anno.core.DataGenResolver;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.datagen.provider.AnnoBlockLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

@ID("drop_self")
public class DropSelfResolver implements DataGenResolver<DropSelf> {
    private final List<Block> blocks = new ArrayList<>();

    @Override
    public void finish(FabricDataGenerator generator, Registration registration) {
        generator.addProvider(new AnnoBlockLootTables(generator, blocks, toAnno().id() + "/" + registration.id()));
    }

    @Override
    public void process(Entry entry, Registration registration) {
        if (entry.is(Block.class)) {
            blocks.add(entry.cast(Block.class));
        }
    }

    @Override
    public Class<DropSelf> aClass() {
        return DropSelf.class;
    }
}
