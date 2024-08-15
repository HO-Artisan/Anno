package ho.artisan.anno.core.resolver.datagen.loot;

import ho.artisan.anno.core.annotation.datagen.loot.DropSelf;
import ho.artisan.anno.core.resolver.DataGenResolver;
import ho.artisan.anno.datagen.provider.loottable.DropSelfLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Block;
import net.minecraft.data.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class DropSelfResolver implements DataGenResolver<DropSelf> {
    private final List<Block> blocks = new ArrayList<>();

    @Override
    public void apply(FabricDataGenerator generator, FabricDataGenerator.Pack pack, Class<?> registration) {
        pack.addProvider((FabricDataGenerator.Pack.RegistryDependentFactory<DataProvider>) (output, registriesFuture) -> new DropSelfLootTableProvider(output, blocks, name()));
    }

    @Override
    public <T> void process(Target<T> target, Class<?> registration) {
        if (target.object() instanceof Block block) {
            this.blocks.add(block);
        }
    }

    @Override
    public Class<DropSelf> annoClass() {
        return DropSelf.class;
    }

    @Override
    public String name() {
        return "Drop Self Loot Table";
    }
}
