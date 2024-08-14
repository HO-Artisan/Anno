package ho.artisan.anno.core.resolver.datagen.model.item;

import ho.artisan.anno.core.annotation.datagen.model.Generated;
import ho.artisan.anno.core.resolver.datagen.model.ModelResolver;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class GeneratedResolver extends ModelResolver<Generated> {
    @Override
    public <T> void process(Target<T> target, Class<?> registration) {
        Item item = (Item) target.object();
        Identifier id = getID(target, registration);
        String texture = target.field().getAnnotation(Generated.class).value().replace("$", "item/" + id.getPath());
        Identifier path = Identifier.of(id.getNamespace(), texture);
        itemModel(generator -> generator.anno$register(item, path, Models.GENERATED));
    }

    @Override
    public Class<Generated> annoClass() {
        return Generated.class;
    }
}
