package ho.artisan.anno.core.resolver.datagen.model.item;

import ho.artisan.anno.core.annotation.datagen.model.Handheld;
import ho.artisan.anno.core.resolver.datagen.model.ModelResolver;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class HandheldResolver extends ModelResolver<Handheld> {
    @Override
    public <T> void process(Target<T> target, Class<?> registration) {
        if (target.object() instanceof Item item) {
            Identifier id = getID(target, registration);
            String texture = target.field().getAnnotation(Handheld.class).value().replace("$", "item/" + id.getPath());
            Identifier path = Identifier.of(id.getNamespace(), texture);
            itemModel(generator -> generator.anno$register(item, path, Models.HANDHELD));
        }
    }

    @Override
    public Class<Handheld> annoClass() {
        return Handheld.class;
    }

    @Override
    public String name() {
        return "Handheld Models";
    }
}
