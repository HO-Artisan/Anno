package ho.artisan.anno.util;

import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public final class AnnoUtil {
    private AnnoUtil() {}

    public static FabricItemGroupBuilder itemGroupBuilder(Identifier id, Class<?> registration) {
        return FabricItemGroupBuilder.create(id).appendItems(list -> {
            for (Entry entry : Registration.wrap(registration).filter(entry -> entry.is(Item.class))) {
                list.add(new ItemStack(entry.cast(Item.class)));
            }
        });
    }
}
