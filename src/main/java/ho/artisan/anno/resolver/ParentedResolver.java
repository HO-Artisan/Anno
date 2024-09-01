package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.ID;
import ho.artisan.anno.annotation.datagen.model.Parented;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

@ID("parented")
public class ParentedResolver extends ModelResolver<Parented> {
    @Override
    public void process(Entry entry, Registration registration) {
        Identifier id = genID(entry, registration);
        Identifier parent = new Identifier(entry.get(Parented.class).value());

        if (entry.is(Item.class)) {
            Item item = entry.cast(Item.class);
            blocks.add(generator -> generator.registerParentedItemModel(item, parent));
        } else if (entry.is(Block.class)) {
            Block block = entry.cast(Block.class);
            blocks.add(generator -> generator.registerParentedItemModel(block, parent));
        }
    }

    @Override
    public Class<Parented> aClass() {
        return Parented.class;
    }
}
