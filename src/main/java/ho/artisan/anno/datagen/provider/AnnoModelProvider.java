package ho.artisan.anno.datagen.provider;

import ho.artisan.anno.util.ModelConductor;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class AnnoModelProvider extends FabricModelProvider {
    private final ModelConductor conductor;

    public AnnoModelProvider(FabricDataGenerator generator, ModelConductor conductor) {
        super(generator);
        this.conductor = conductor;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        conductor.blocks().forEach(consumer -> consumer.accept(generator));
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        conductor.items().forEach(consumer -> consumer.accept(generator));
    }
}
