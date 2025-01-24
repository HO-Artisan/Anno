package ho.artisan.anno.core;

import org.jetbrains.annotations.ApiStatus;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @ApiStatus.Experimental
    public <A extends Annotation> void add(A annotation) {
        map.put(annotation.annotationType(), annotation);
    }

    @ApiStatus.Experimental
    public <A extends Annotation> void remove(Class<A> aClass) {
        map.remove(aClass);
    }
}
