package ho.artisan.anno.annotation.lang;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lang {
    Value[] value();

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Value {
        String code() default "en_us";

        String text();

        String[] tips() default {};
    }
}
