package ho.artisan.anno.datagen.provider.loottable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DropSelfLootTableProvider extends FabricBlockLootTableProvider {
    private final List<Block> blocks;
    private final String name;

    public DropSelfLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> lookup, List<Block> blocks, String name) {
        super(dataOutput, lookup);
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
