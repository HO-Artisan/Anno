package ho.artisan.anno;

import ho.artisan.anno.annotation.ID;
import ho.artisan.anno.annotation.datagen.DropSelf;
import ho.artisan.anno.annotation.datagen.Lang;
import ho.artisan.anno.annotation.datagen.model.Parented;
import ho.artisan.anno.annotation.datagen.model.CubeAll;
import ho.artisan.anno.annotation.datagen.model.Generated;
import ho.artisan.anno.annotation.vanilla.Fuel;
import ho.artisan.anno.annotation.vanilla.Reg;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

@ID("anno")
public class AnnoRegistration {
    @DropSelf
    @Lang(langCode = "en_us", value = "Iron Coal Block")
    @Lang(langCode = "zh_cn", value = "铁煤炭块")
    @Reg("block")
    @ID("iron_coal_block")
    @CubeAll
    public static final Block IRON_COAL_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK));
    @Lang(langCode = "en_us", value = "Iron Coal")
    @Lang(langCode = "zh_cn", value = "铁煤炭")
    @Fuel(2400)
    @Reg("item")
    @ID("iron_coal")
    @Generated
    public static final Item IRON_COAL = new Item(new Item.Settings());
    @Fuel(24000)
    @Reg("item")
    @ID("iron_coal_block")
    @Parented("block/iron_coal_block")
    public static final BlockItem IRON_COAL_BLOCK_ITEM = new BlockItem(IRON_COAL_BLOCK, new Item.Settings());
}

