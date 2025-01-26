package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.Fuel;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.core.resolver.Resolver;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;

public final class FuelResolver implements Resolver {
    @Override
    public boolean match(Entry entry) {
        return entry.is(Item.class) && entry.contain(Fuel.class);
    }

    @Override
    public void process(Entry entry, Registration registration) {
        Item item = entry.cast(Item.class);
        Fuel fuel = entry.get(Fuel.class);
        FuelRegistry.INSTANCE.add(item, fuel.time());
    }

    @Override
    public String id() {
        return "fuel";
    }
}
