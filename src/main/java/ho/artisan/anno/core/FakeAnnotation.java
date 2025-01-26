package ho.artisan.anno.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 伪造注解的动态接口类，使用依赖注入模式。
 * @param <A> 伪造的注解类型
 */
public final class FakeAnnotation<A> implements InvocationHandler {
    private final Class<A> aClass;
    private final Map<String, Object> valueMap;

    private FakeAnnotation(Class<A> aClass, final Map<String, Object> valueMap) {
        this.aClass = aClass;
        this.valueMap = valueMap;
    }

    public static <A> Builder<A> builder(Class<A> aClass) {
        return new Builder<>(aClass);
    }

    public A fake() {
        return aClass.cast(Proxy.newProxyInstance(
                aClass.getClassLoader(),
                new Class[]{Annotation.class, aClass},
                this
        ));
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) {
        if (valueMap.containsKey(method.getName()))
            return valueMap.get(method.getName());
        else
            throw new RuntimeException(method.getName() + "'s \"key to value\" wasn't found in FakeAnnotation!");
    }

    public static class Builder<A> {
        private final Class<A> aClass;
        private final Map<String, Object> valueMap;

        private Builder(Class<A> aClass) {
            this.aClass = aClass;
            this.valueMap = new HashMap<>();
        }

        /**
         * @param key 伪造注解的方法名
         * @param value 伪造注解的值
         */
        public <T> Builder<A> fake(String key, T value) {
            valueMap.put(key, value);
            return this;
        }

        public A build() {
            return new FakeAnnotation<>(aClass, valueMap).fake();
        }
    }
}
