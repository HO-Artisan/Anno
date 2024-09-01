package ho.artisan.anno.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

import java.util.List;
import java.util.function.Consumer;

public class AnnoModels extends FabricModelProvider {
    private final List<Consumer<BlockStateModelGenerator>> blocks;
    private final List<Consumer<ItemModelGenerator>> items;
    private final String name;

    public AnnoModels(FabricDataGenerator generator, List<Consumer<BlockStateModelGenerator>> blocks, List<Consumer<ItemModelGenerator>> items, String name) {
        super(generator);
        this.blocks = blocks;
        this.items = items;
        this.name = name;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        blocks.forEach(consumer -> consumer.accept(generator));
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        items.forEach(consumer -> consumer.accept(generator));
    }

    @Override
    public String getName() {
        return "Models/" + name;
    }
}
