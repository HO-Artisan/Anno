package ho.artisan.anno.core.resolver.vanilla;

import ho.artisan.anno.core.annotation.vanilla.Burnable;
import ho.artisan.anno.core.resolver.Resolver;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;

public class FuelResolver implements Resolver<Burnable> {

    @Override
    public <T> void process(Target<T> target, Class<?> registration) {
        if (target.object() instanceof Item item) {
            int time = target.field().getAnnotation(Burnable.class).value();
            FuelRegistry.INSTANCE.add(item, time);
        }
    }

    @Override
    public Class<Burnable> annoClass() {
        return Burnable.class;
    }

    @Override
    public String name() {
        return "Fuel";
    }
}
