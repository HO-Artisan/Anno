package ho.artisan.anno.datagen.provider.loottable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;

import java.util.List;

public class DropSelfLootTableProvider extends FabricBlockLootTableProvider {
    private final List<Block> blocks;

    public DropSelfLootTableProvider(FabricDataOutput dataOutput, List<Block> blocks) {
        super(dataOutput);
        this.blocks = blocks;
    }

    @Override
    public void generate() {
        blocks.forEach(this::addDrop);
    }
}
