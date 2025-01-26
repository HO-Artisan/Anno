package ho.artisan.anno.resolver;

import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.core.resolver.DataGenerationResolver;
import ho.artisan.anno.datagen.provider.AnnoModelProvider;
import ho.artisan.anno.util.ModelConductor;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Block;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

public final class SimpleModelResolver implements DataGenerationResolver {
    private final ModelConductor conductor = ModelConductor.create();

    @Override
    public void load(FabricDataGenerator generator) {
        generator.addProvider(new AnnoModelProvider(generator, conductor));
    }

    @Override
    public boolean match(Entry entry) {
        return entry.is(Block.class) || entry.is(Item.class);
    }

    @Override
    public void process(Entry entry, Registration registration) {
        if (entry.is(Block.class)) {
            Block block = entry.cast(Block.class);
            conductor.block(generator -> generator.registerSimpleCubeAll(block));
        }else if (entry.is(Item.class)) {
            Item item = entry.cast(Item.class);
            conductor.item(generator -> generator.register(item, Models.GENERATED));
        }
    }

    @Override
    public String id() {
        return "simple_model";
    }
}
