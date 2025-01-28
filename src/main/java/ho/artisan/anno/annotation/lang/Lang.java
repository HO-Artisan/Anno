package ho.artisan.anno.annotation.lang;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(LangContainer.class)
public @interface Lang {
    String code();

    String text();

    String suffix() default "";
}
