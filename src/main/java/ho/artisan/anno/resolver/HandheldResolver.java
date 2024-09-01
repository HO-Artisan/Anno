package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.ID;
import ho.artisan.anno.annotation.datagen.model.Handheld;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

@ID("handheld")
public class HandheldResolver extends ModelResolver<Handheld> {
    @Override
    public void process(Entry entry, Registration registration) {
        if (entry.is(Item.class)) {
            Item item = entry.cast(Item.class);
            String suffix = entry.get(aClass()).suffix();
            if (suffix.isBlank())
                items.add(generator -> generator.register(item, Models.HANDHELD));
            else
                items.add(generator -> generator.register(item, suffix, Models.HANDHELD));
        }
    }

    @Override
    public Class<Handheld> aClass() {
        return Handheld.class;
    }
}
