package ho.artisan.anno.core.resolver.datagen.model;

import ho.artisan.anno.core.resolver.DataGenResolver;
import ho.artisan.anno.datagen.provider.AnnoModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ModelResolver<A extends Annotation> implements DataGenResolver<A> {
    private final List<Consumer<BlockStateModelGenerator>> blocks = new ArrayList<>();
    private final List<Consumer<ItemModelGenerator>> items = new ArrayList<>();

    @Override
    public void apply(FabricDataGenerator generator) {
        AnnoModelProvider modelProvider = new AnnoModelProvider(generator);
        modelProvider.blocks.addAll(this.blocks);
        modelProvider.items.addAll(this.items);
        generator.addProvider(modelProvider);
    }

    public void blockModel(Consumer<BlockStateModelGenerator> consumer) {
        blocks.add(consumer);
    }

    public void itemModel(Consumer<ItemModelGenerator> consumer) {
        items.add(consumer);
    }
}
