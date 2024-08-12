package ho.artisan.anno;

import ho.artisan.anno.core.annotation.ID;
import ho.artisan.anno.core.annotation.datagen.Lang;
import ho.artisan.anno.core.annotation.datagen.model.Generated;
import ho.artisan.anno.core.annotation.vanilla.Burnable;
import ho.artisan.anno.core.annotation.vanilla.Reg;
import net.minecraft.item.Item;

@ID("anno")
public class AnnoRegistration {
    @Generated
    @Lang(langCode = "en_us", value = "Iron Coal")
    @Lang(langCode = "zh_cn", value = "铁煤炭")
    @Burnable(4800)
    @Reg("item")
    @ID("iron_coal")
    public static final Item IRON_COAL = new Item(new Item.Settings());
}

