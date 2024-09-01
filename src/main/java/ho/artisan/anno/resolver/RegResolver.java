package ho.artisan.anno.resolver;

import ho.artisan.anno.core.Entry;
import ho.artisan.anno.core.Registration;
import ho.artisan.anno.annotation.ID;
import ho.artisan.anno.annotation.Priority;
import ho.artisan.anno.annotation.vanilla.Reg;
import ho.artisan.anno.core.Resolver;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@ID("registry")
@Priority(100)
public class RegResolver implements Resolver<Reg> {
    @SuppressWarnings("unchecked")
    @Override
    public void process(Entry entry, Registration registration) {
        Object o = entry.cast(Object.class);
        Reg reg = entry.get(Reg.class);
        Registry<? super Object> registry = (Registry<? super Object>) Registry.REGISTRIES.get(new Identifier(reg.value()));
        if (registry != null) {
            Registry.register(registry, genID(entry, registration), o);
        }
    }

    @Override
    public Class<Reg> aClass() {
        return Reg.class;
    }
}
