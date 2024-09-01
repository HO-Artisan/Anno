package ho.artisan.anno.core;

import ho.artisan.anno.annotation.TargetType;
import net.minecraft.util.Identifier;

import java.lang.annotation.Annotation;

public interface Resolver<A extends Annotation> {
    void process(Entry entry, Registration registration);

    Class<A> aClass();

    default Identifier genID(Entry entry, Registration registration) {
        return new Identifier(registration.id(), entry.id());
    }

    default boolean isSuitable(Entry entry) {
        for (Class<?> aClass : new Anno(aClass()).get(TargetType.class).value()) {
            if (aClass == Object.class)
                return true;
            else if (!entry.is(aClass)) {
                return false;
            }
        }
        return isRepeated(entry) ? !entry.getRepeated(aClass()).isEmpty() : entry.has(aClass());
    }

    default boolean isRepeated(Entry entry) {
        return false;
    }

    default Anno toAnno() {
        return new Anno(getClass());
    }
}
