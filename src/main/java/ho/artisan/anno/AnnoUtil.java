package ho.artisan.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnoUtil {
    public static <T extends Annotation, A> A getValue(T target, Class<A> aClass) {
        try {
            Method method = target.getClass().getMethod("value");
            method.setAccessible(true);
            return aClass.cast(method.invoke(target));
        } catch (Exception e) {
            return null;
        }
    }
}
