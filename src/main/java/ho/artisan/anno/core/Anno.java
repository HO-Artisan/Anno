package ho.artisan.anno.core;

import com.google.common.reflect.TypeToken;
import ho.artisan.anno.AnnoUtil;
import ho.artisan.anno.annotation.ID;
import ho.artisan.anno.annotation.Priority;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Anno is a class containing annotations, they will be saved as a list at initialization time.
 */
public class Anno {
    private final List<Annotation> annotations = new ArrayList<>();

    public Anno(AnnotatedElement element) {
        Collections.addAll(annotations, element.getAnnotations());
    }

    /**
     * Adding other Anno's the designated annotation to this.
     */
    public <A extends Annotation> void add(Anno anno, Class<A> aClass) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() != aClass)
                annotations.add(anno.get(aClass));
        }
    }

    /**
     * A method which can get repeated annotation, it will return as a list.
     */
    @SuppressWarnings({"UnstableApiUsage", "unchecked"})
    public <A extends Annotation> List<A> getRepeated(Class<A> aClass) {
        Class<? extends Annotation> repeatable = new Anno(aClass).repeated();
        if (repeatable == null) {
            return List.of();
        }
        A[] a = (A[]) AnnoUtil.getValue(get(repeatable), new TypeToken<A[]>(){}.getRawType());
        return a == null ? List.of() : List.of(a);
    }

    /**
     * Whether this has the designated annotation.
     */
    public <A extends Annotation> boolean has(Class<A> aClass) {
        return get(aClass) != null;
    }

    /**
     * Get the designated annotation.
     */
    public <A extends Annotation> A get(Class<A> aClass) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == aClass)
                return aClass.cast(annotation);
        }
        return null;
    }

    /**
     * Return this Anno`s id, which is located in {@link ID} annotation.
     */
    public String id() {
        return get(ID.class).value();
    }

    /**
     * Return this Anno`s priority, which is located {@link Priority} annotation.
     */
    public int priority() {
        Priority priority = get(Priority.class);
        if (priority == null)
            return 0;
        return priority.value();
    }

    /**
     * Get this Anno`s {@link Repeatable} annotation if any.
     */
    @Nullable
    public Class<? extends Annotation> repeated() {
        Repeatable repeatable = get(Repeatable.class);
        if (repeatable == null) {
            return null;
        }
        return repeatable.value();
    }

    /**
     * Get this Anno`s all annotations.
     */
    public List<Annotation> getAnnotations() {
        return List.copyOf(annotations);
    }

    @Override
    public String toString() {
        return "Anno:" + id();
    }
}
