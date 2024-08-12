package ho.artisan.anno.core.resolver.vanilla;

import ho.artisan.anno.core.annotation.vanilla.Reg;
import ho.artisan.anno.core.resolver.Resolver;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegResolver implements Resolver<Reg> {

    @SuppressWarnings("unchecked")
    @Override
    public <T> void process(Target<T> target, Class<?> registration) {
        Identifier id = getID(target, registration);
        try {
            String name = target.field().getAnnotation(Reg.class).value().toUpperCase();
            Registry<T> registry = (Registry<T>) Registry.class.getField(name).get(null);
            Registry.register(registry, id, target.object());
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
    }

    @Override
    public Class<Reg> annoClass() {
        return Reg.class;
    }
}
