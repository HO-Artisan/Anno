package ho.artisan.anno.core.resolver;

import ho.artisan.anno.core.annotation.ID;
import ho.artisan.anno.core.annotation.TargetType;
import net.minecraft.util.Identifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

public interface Resolver<A extends Annotation> {
    <T> void process(Target<T> target, Class<?> registration);
    Class<A> annoClass();
    String name();

    default void process(Field field, Class<?> clazz) {
        if (isAnnotated(field) && wrap(field) != null) {
            process(new Target<>(field, wrap(field)), clazz);
        }
    }

    default <T> String getID(AnnotatedElement annotatedElement) {
        return annotatedElement.getAnnotation(ID.class).value().toLowerCase();
    }

    default <T> Identifier getID(Target<T> target, Class<?> registration) {
        return Identifier.of(getID(registration), getID(target.field));
    }

    default boolean condition(Field field) {
        return true;
    }

    @SuppressWarnings("unchecked")
    default <T> Target<T> wrap(Field field) {
        field.setAccessible(true);
        try {
            Object object = field.get(null);
            if (object == null)
                return null;
            if (isAnnotated(field)) {
                for (Class<?> tClass : annoClass().getAnnotation(TargetType.class).value()) {
                    if (tClass == Object.class)
                        return (Target<T>) new Target<>(field, object);
                    else if (tClass.isInstance(object))
                        return (Target<T>) new Target<>(field, tClass.cast(object));
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    default boolean isAnnotated(Field field) {
        return field.isAnnotationPresent(annoClass());
    }

    record Target<O>(Field field, O object) {
        @Override
        public String toString() {
            return "Target{" +
                    "field=" + field +
                    ", object=" + object +
                    '}';
        }
    }
}
