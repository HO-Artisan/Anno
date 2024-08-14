package ho.artisan.anno.mixin;

import ho.artisan.anno.datagen.AnnoItemModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemModelGenerator.class)
public class ItemModelGeneratorMixin implements AnnoItemModelGenerator {
    @Override
    public void anno$register(Item item, Identifier texture, Model model) {
        ItemModelGenerator generator = (ItemModelGenerator) (Object) this;
        model.upload(ModelIds.getItemModelId(item), TextureMap.layer0(texture), generator.writer);
    }

    @Override
    public void anno$register(Identifier item, Identifier texture, Model model) {
        ItemModelGenerator generator = (ItemModelGenerator) (Object) this;
        Identifier id = Identifier.of(item.getNamespace(), "item/" + item.getPath());
        model.upload(id, TextureMap.layer0(texture), generator.writer);
    }
}
