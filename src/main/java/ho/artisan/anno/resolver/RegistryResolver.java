package ho.artisan.anno.resolver;

import ho.artisan.anno.annotation.RegistryType;
import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.core.resolver.Resolver;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class RegistryResolver implements Resolver {
    @Override
    public boolean match(Entry entry) {
        return entry.contain(RegistryType.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(Entry entry, Registration registration) {
        RegistryType type = entry.get(RegistryType.class);
        Registry<Object> registry = (Registry<Object>) Registry.REGISTRIES.get(new Identifier(type.value()));
        if (registry != null)
            Registry.register(registry, new Identifier(registration.id(), entry.id()), entry.cast(Object.class));
        else
            throw new RuntimeException("Can't find Registry Type:" + type.value());
    }

    @Override
    public String id() {
        return "entry";
    }

    @Override
    public int priority() {
        return Integer.MAX_VALUE;
    }
}
