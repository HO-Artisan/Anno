package ho.artisan.anno.util;

import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public record ModelConductor(List<Consumer<BlockStateModelGenerator>> blocks, List<Consumer<ItemModelGenerator>> items) {
    public void block(Consumer<BlockStateModelGenerator> consumer) {
        blocks.add(consumer);
    }

    public void item(Consumer<ItemModelGenerator> consumer) {
        items.add(consumer);
    }

    public static ModelConductor create() {
        return new ModelConductor(new ArrayList<>(), new ArrayList<>());
    }
}
