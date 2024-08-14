package ho.artisan.anno.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

import java.util.List;
import java.util.function.Consumer;

public class AnnoModelProvider extends FabricModelProvider {
    private final List<Consumer<BlockStateModelGenerator>> blocks;
    private final List<Consumer<ItemModelGenerator>> items;

    public AnnoModelProvider(FabricDataOutput output, List<Consumer<BlockStateModelGenerator>> blocks, List<Consumer<ItemModelGenerator>> items) {
        super(output);
        this.blocks = blocks;
        this.items = items;
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
        return "Anno Models";
    }
}
