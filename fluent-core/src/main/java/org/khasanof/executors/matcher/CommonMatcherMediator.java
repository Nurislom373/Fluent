package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.enums.HandleType;
import org.khasanof.service.FindBeansOfTypeService;
import org.springframework.beans.factory.InitializingBean;

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
@SuppressWarnings({"rawtypes", "unchecked"})
public class CommonMatcherMediator implements InitializingBean {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final Map<Class<? extends Annotation>, AbstractMatcher> matchers = new HashMap<>();

    public CommonMatcherMediator(FindBeansOfTypeService findBeansOfTypeService) {
        this.findBeansOfTypeService = findBeansOfTypeService;
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
        findBeansOfTypeService.findAllByList(AbstractMatcher.class)
                .forEach(this::addMatcher);
    }

    private void addMatcher(AbstractMatcher instance) {
        if (!Modifier.isAbstract(instance.getClass().getModifiers())) {
            matchers.put(instance.getType(), instance);
        }
    }
}
