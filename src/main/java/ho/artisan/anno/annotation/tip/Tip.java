package ho.artisan.anno.annotation.tip;

import net.minecraft.util.Formatting;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TipContainer.class)
public @interface Tip {
    String text();
    String code();
    Formatting[] formatting();
}
