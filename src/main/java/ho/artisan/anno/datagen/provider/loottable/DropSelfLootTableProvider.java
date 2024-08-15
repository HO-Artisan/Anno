package ho.artisan.anno.datagen.provider.loottable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;

import java.util.List;

public class DropSelfLootTableProvider extends FabricBlockLootTableProvider {
    private final List<Block> blocks;
    private final String name;

    public DropSelfLootTableProvider(FabricDataOutput dataOutput, List<Block> blocks, String name) {
        super(dataOutput);
        this.blocks = blocks;
        this.name = name;
    }

    @Override
    public void generate() {
        blocks.forEach(this::addDrop);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
