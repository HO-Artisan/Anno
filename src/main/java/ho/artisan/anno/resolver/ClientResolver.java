package ho.artisan.anno.resolver;

import ho.artisan.anno.core.resolver.Resolver;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface ClientResolver extends Resolver {
}
