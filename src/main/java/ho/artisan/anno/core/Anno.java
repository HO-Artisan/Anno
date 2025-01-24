package ho.artisan.anno.core;

import ho.artisan.anno.core.annotation.ID;
import ho.artisan.anno.core.annotation.Priority;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Anno 是一个用于管理注解的操作单元。
 */
public class Anno {
    private final Map<Class<? extends Annotation>, Annotation> map;

    protected Anno(AnnotatedElement element) {
        map = Arrays.stream(element.getDeclaredAnnotations()).collect(
                Collectors.toMap(Annotation::annotationType, Function.identity(), (first, second) -> first, LinkedHashMap::new)
        );
    }

    public <A extends Annotation> A get(Class<A> aClass) {
        return aClass.cast(map.get(aClass));
    }

    public <A extends Annotation> boolean contain(Class<A> aClass) {
        return aClass.isInstance(map.get(aClass));
    }

    public <A extends Annotation> void add(A annotation) {
        map.put(annotation.annotationType(), annotation);
    }

    public <A extends Annotation> void remove(Class<A> aClass) {
        map.remove(aClass);
    }

    public String id() {
        return get(ID.class).value();
    }

    public int priority() {
        return get(Priority.class).value();
    }
}
