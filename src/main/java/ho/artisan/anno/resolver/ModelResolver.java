package ho.artisan.anno.resolver;

import ho.artisan.anno.core.DataGenResolver;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.datagen.provider.AnnoModels;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ModelResolver<A extends Annotation> implements DataGenResolver<A> {
    protected final List<Consumer<BlockStateModelGenerator>> blocks = new ArrayList<>();
    protected final List<Consumer<ItemModelGenerator>> items = new ArrayList<>();

    @Override
    public void finish(FabricDataGenerator generator, Registration registration) {
        String name = toAnno().id() + "/" + registration.id();
        generator.addProvider(new AnnoModels(generator, blocks, items, name));
    }
}
