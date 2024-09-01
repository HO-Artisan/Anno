package ho.artisan.anno.annotation.datagen;

import ho.artisan.anno.annotation.TargetType;

import java.lang.annotation.*;

@TargetType(Object.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(LangContainer.class)
public @interface Lang {
    String langCode();
    String value();
    String key() default "$";
}
