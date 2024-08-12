package ho.artisan.anno.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AnnoModelProvider extends FabricModelProvider {
    public final List<Consumer<BlockStateModelGenerator>> blocks = new ArrayList<>();
    public final List<Consumer<ItemModelGenerator>> items = new ArrayList<>();

    public AnnoModelProvider(FabricDataGenerator generator) {
        super(generator);
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
