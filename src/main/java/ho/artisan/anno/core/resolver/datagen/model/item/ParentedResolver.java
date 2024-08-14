package ho.artisan.anno.core.resolver.datagen.model.item;

import ho.artisan.anno.core.annotation.datagen.model.Parented;
import ho.artisan.anno.core.resolver.datagen.model.ModelResolver;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ParentedResolver extends ModelResolver<Parented> {
    @Override
    public <T> void process(Target<T> target, Class<?> registration) {
        Identifier id = getID(target, registration);
        Identifier parent = Identifier.of(id.getNamespace(), target.field().getAnnotation(Parented.class).value());
        if (target.object() instanceof Item item) {
            blockModel(generator -> generator.registerParentedItemModel(item, parent));
        }else if (target.object() instanceof Block block) {
            blockModel(generator -> generator.registerParentedItemModel(block, parent));
        }
    }

    @Override
    public Class<Parented> annoClass() {
        return Parented.class;
    }
}
