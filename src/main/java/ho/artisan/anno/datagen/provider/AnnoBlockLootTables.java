package ho.artisan.anno.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;

import java.util.List;

public class AnnoBlockLootTables extends FabricBlockLootTableProvider {
    private final List<Block> blocks;
    private final String name;

    public AnnoBlockLootTables(FabricDataGenerator dataGenerator, List<Block> blocks, String name) {
        super(dataGenerator);
        this.blocks = blocks;
        this.name = name;
    }

    @Override
    protected void generateBlockLootTables() {
        blocks.forEach(this::addDrop);
    }

    @Override
    public String getName() {
        return "LootTables/" + name;
    }
}
