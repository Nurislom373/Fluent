package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.enums.HandleClasses;
import org.khasanof.enums.HandleType;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 24.06.2023 1:14
 */
@Component
public class CompositeMatcher {

    public final Map<Class<? extends Annotation>, Supplier<GenericMatcher>> supplierMap = new HashMap<>();
    private final AdapterMatcher adapterMatcher;

    public CompositeMatcher(AdapterMatcher adapterMatcher) {
        this.adapterMatcher = adapterMatcher;
        setSupplierMap();
    }

    @SuppressWarnings("unchecked")
    public boolean chooseMatcher(Method method, Object value, Class<? extends Annotation> annotation) {
        return supplierMap.get(annotation).get()
                .matcher(method.getAnnotation(annotation), value);
    }

    @SuppressWarnings("unchecked")
    public boolean chooseMatcher(Method method, HandleType handleType) {
        return supplierMap.get(HandleAny.class)
                .get().matcher(method.getAnnotation(HandleAny.class), handleType);
    }

    void setSupplierMap() {
        adapterMatcher.setUp(HandleClasses.getAllAnnotations(), supplierMap);
    }

}
