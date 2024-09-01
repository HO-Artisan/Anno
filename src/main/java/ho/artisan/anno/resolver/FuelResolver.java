package ho.artisan.anno.resolver;

import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.annotation.ID;
import ho.artisan.anno.annotation.vanilla.Fuel;
import ho.artisan.anno.core.Resolver;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;

@ID("fuel")
public class FuelResolver implements Resolver<Fuel> {
    @Override
    public void process(Entry entry, Registration registration) {
        if (entry.is(Item.class)) {
            Item item = entry.cast(Item.class);
            Fuel fuel = entry.get(Fuel.class);
            FuelRegistry.INSTANCE.add(item, fuel.value());
        }
    }

    @Override
    public Class<Fuel> aClass() {
        return Fuel.class;
    }
}
