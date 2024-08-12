package ho.artisan.anno.core.resolver;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Field;

public interface RepeatableResolver<A extends Annotation> extends Resolver<A> {
    @Override
    default boolean isAnnotated(Field field) {
        return field.isAnnotationPresent(containerClass());
    }

    default Class<? extends Annotation> containerClass() {
        return annoClass().getAnnotation(Repeatable.class).value();
    }
}
