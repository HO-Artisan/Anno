package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.tip.Tip;
import ho.artisan.anno.annotation.tip.TipContainer;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.core.resolver.ClientResolver;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.item.Item;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public final class TipResolver implements ClientResolver {
    @Override
    public boolean match(Entry entry) {
        return entry.is(Item.class) && entry.contain(TipContainer.class);
    }

    @Override
    public void process(Entry entry, Registration registration) {
        Item item = entry.cast(Item.class);
        Tip[] tips = entry.get(TipContainer.class).value();
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            if (stack.isOf(item)) {
                for (Tip tip : tips) {
                    lines.add(new TranslatableText(tip.text()).formatted(tip.formatting()));
                }
            }
        });
    }

    @Override
    public String id() {
        return "tip";
    }

    @Override
    public int priority() {
        return 10;
    }
}
