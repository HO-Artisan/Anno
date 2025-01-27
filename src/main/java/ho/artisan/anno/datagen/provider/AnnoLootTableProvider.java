package ho.artisan.anno.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.BiConsumer;

public final class AnnoLootTableProvider extends SimpleFabricLootTableProvider {
    private final Map<Identifier, LootTable.Builder> map;

    public AnnoLootTableProvider(FabricDataGenerator generator, LootContextType type, Map<Identifier, LootTable.Builder> map) {
        super(generator, type);
        this.map = map;
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> consumer) {
        map.forEach(consumer);
    }
}
