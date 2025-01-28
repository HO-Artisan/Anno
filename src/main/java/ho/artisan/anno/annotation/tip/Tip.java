package ho.artisan.anno.annotation.tip;

import net.minecraft.util.Formatting;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tip {
    String suffix() default "tip";
    Formatting[] formatting();
}
