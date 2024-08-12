package ho.artisan.anno.datagen;

import net.minecraft.data.client.Model;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public interface AnnoItemModelGenerator {
    default void anno$register(Item item, Identifier texture, Model model) {}

    default void anno$register(Identifier item, Identifier texture, Model model) {}
}
