package ho.artisan.anno.annotation.datagen;

import ho.artisan.anno.annotation.TargetType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@TargetType(Object.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LangContainer {
    Lang[] value();
}
