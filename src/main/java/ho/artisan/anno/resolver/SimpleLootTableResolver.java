package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.loot.SimpleLootTable;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.datagen.provider.AnnoLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Block;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public final class SimpleLootTableResolver implements DataGenerationResolver {
    private final Map<Identifier, LootTable.Builder> map = new HashMap<>();

    @Override
    public void load(FabricDataGenerator generator, Registration registration) {
        generator.addProvider(new AnnoLootTableProvider(generator, LootContextTypes.BLOCK, map));
    }

    @Override
    public boolean match(Entry entry) {
        return entry.is(Block.class) && entry.contain(SimpleLootTable.class);
    }

    @Override
    public void process(Entry entry, Registration registration) {
        Block block = entry.cast(Block.class);
        map.put(new Identifier(registration.id(), entry.id()), BlockLootTableGenerator.drops(block));
    }

    @Override
    public String id() {
        return "simple_loot_table";
    }
}
