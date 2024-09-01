package ho.artisan.anno.core;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.lang.annotation.Annotation;

@Environment(EnvType.CLIENT)
public interface ClientResolver<A extends Annotation> extends Resolver<A> {

}
