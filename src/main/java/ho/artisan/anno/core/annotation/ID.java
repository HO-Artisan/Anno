package ho.artisan.anno.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TargetType(Object.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ID {
    String value();
}
