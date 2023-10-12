package org.khasanof.executors.matcher;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 24.06.2023 1:14
 */
@Component
public class AdapterMatcher implements InitializingBean {

    private final ApplicationContext applicationContext;
    private final Map<Class<? extends Annotation>, GenericMatcher> matchers = new HashMap<>();

    public AdapterMatcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setUp(Set<Class<? extends Annotation>> classes, Map<Class<? extends Annotation>, Supplier<GenericMatcher>> supplierMap) {
        classes.stream().map(clazz -> new AbstractMap.SimpleEntry<>(clazz, (Supplier<GenericMatcher>) () -> findMatcher(clazz)))
                .forEach(classSupplierSimpleEntry -> supplierMap.put(classSupplierSimpleEntry.getKey(),
                        classSupplierSimpleEntry.getValue()));
    }

    public GenericMatcher findMatcher(Class<? extends Annotation> annotation) {
        return matchers.get(annotation);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() {
        applicationContext.getBeansOfType(GenericMatcher.class).forEach((beanName, instance) -> {
            if (!Modifier.isAbstract(instance.getClass().getModifiers())) {
                matchers.put(instance.getType(), instance);
            }
        });
    }

}
