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

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 24.06.2023 1:14
 */
@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class MatcherMediator implements InitializingBean {

    private final Map<Class<? extends Annotation>, AbstractMatcher> matchers = new HashMap<>();
    private final ApplicationContext applicationContext;

    public MatcherMediator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public boolean match(Method method, Object value, Class<? extends Annotation> annotation) {
        return matchers.get(annotation)
                .matcher(method.getAnnotation(annotation), value);
    }

    public boolean match(Method method, HandleType handleType) {
        return matchers.get(HandleAny.class)
                .matcher(method.getAnnotation(HandleAny.class), handleType);
    }

    @Override
    public void afterPropertiesSet() {
        internalAfterPropertiesSet();
    }

    private void internalAfterPropertiesSet() {
        applicationContext.getBeansOfType(AbstractMatcher.class)
                .forEach((beanName, bean) -> addMatcher(bean));
    }

    private void addMatcher(AbstractMatcher instance) {
        if (!Modifier.isAbstract(instance.getClass().getModifiers())) {
            matchers.put(instance.getType(), instance);
        }
    }
}
