package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.enums.HandleType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 24.06.2023 1:14
 */
@Component
@SuppressWarnings({"rawtypes"})
public class CommonMatcher implements InitializingBean {

    private final Map<Class<? extends Annotation>, GenericMatcher> matchers = new HashMap<>();
    private final ApplicationContext applicationContext;

    public CommonMatcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public boolean chooseMatcher(Method method, Object value, Class<? extends Annotation> annotation) {
        return matchers.get(annotation)
                .matcher(method.getAnnotation(annotation), value);
    }

    @SuppressWarnings("unchecked")
    public boolean chooseMatcher(Method method, HandleType handleType) {
        return matchers.get(HandleAny.class)
                .matcher(method.getAnnotation(HandleAny.class), handleType);
    }

    @Override
    public void afterPropertiesSet() {
        tryAfterPropertiesSet();
    }

    private void tryAfterPropertiesSet() {
        applicationContext.getBeansOfType(GenericMatcher.class)
                .forEach((beanName, bean) -> addMatcher(bean));
    }

    @SuppressWarnings({"unchecked"})
    private void addMatcher(GenericMatcher instance) {
        if (!Modifier.isAbstract(instance.getClass().getModifiers())) {
            matchers.put(instance.getType(), instance);
        }
    }

}
