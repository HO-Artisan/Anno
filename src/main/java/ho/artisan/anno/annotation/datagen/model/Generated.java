package ho.artisan.anno.annotation.datagen.model;

import ho.artisan.anno.annotation.TargetType;
import net.minecraft.item.Item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@TargetType(Item.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Generated {
    String suffix() default "";
}
