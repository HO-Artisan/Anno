package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.RegistryType;
import ho.artisan.anno.annotation.lang.Lang;
import ho.artisan.anno.annotation.tip.Tip;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.item.Item;
import net.minecraft.text.TranslatableText;

import java.util.Arrays;
import java.util.List;

@Environment(EnvType.CLIENT)
public final class TipResolver implements ClientResolver {

    @Override
    public boolean match(Entry entry) {
        return entry.is(Item.class) && entry.contain(Tip.class) && entry.contain(RegistryType.class);
    }

    @Override
    public void process(Entry entry, Registration registration) {
        Item item = entry.cast(Item.class);
        RegistryType type = entry.get(RegistryType.class);
        Tip tip = entry.get(Tip.class);
        List<Lang.Value> values = Arrays.stream(entry.get(Lang.class).value()).filter(value -> value.code().equals("en_us")).toList();

        for (int i = 0; i < values.size(); i++) {
            String key = type.value() + '.' + registration.id() + '.' + entry.id() + ".tip." + i;
            ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
                if (stack.isOf(item)) {
                    lines.add(new TranslatableText(key).formatted(tip.formatting()));
                }
            });
        }
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
