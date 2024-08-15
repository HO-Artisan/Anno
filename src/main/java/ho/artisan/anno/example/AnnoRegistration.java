package ho.artisan.anno.example;

import ho.artisan.anno.core.annotation.ID;
import ho.artisan.anno.core.annotation.datagen.Lang;
import ho.artisan.anno.core.annotation.datagen.loot.DropSelf;
import ho.artisan.anno.core.annotation.datagen.model.CubeAll;
import ho.artisan.anno.core.annotation.datagen.model.Generated;
import ho.artisan.anno.core.annotation.datagen.model.Parented;
import ho.artisan.anno.core.annotation.vanilla.Burnable;
import ho.artisan.anno.core.annotation.vanilla.Reg;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
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
    @DropSelf
    @CubeAll
    @Lang(langCode = "en_us", value = "Iron Coal Block")
    @Lang(langCode = "zh_cn", value = "铁煤炭块")
    @Reg("block")
    @ID("iron_coal_block")
    public static final Block IRON_COAL_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK));
    @Parented("block/iron_coal_block")
    @Burnable(48000)
    @Reg("item")
    @ID("iron_coal_block")
    public static final BlockItem IRON_COAL_BLOCK_ITEM = new BlockItem(IRON_COAL_BLOCK, new Item.Settings());
}

