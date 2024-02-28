package org.khasanof.factories.condition;

import org.khasanof.annotation.condition.Condition;
import org.khasanof.feature.condition.FluentCondition;
import org.khasanof.registry.condition.FluentConditionRegistry;
import org.springframework.beans.BeanUtils;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.factories.condition
 * @since 2/28/2024 12:04 AM
 */
public class DefaultFluentConditionFactory implements FluentConditionFactory {

    private final FluentConditionRegistry registry;

    public DefaultFluentConditionFactory(FluentConditionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public FluentCondition create(Annotation annotation) {
        return createInternal(annotation);
    }

    private FluentCondition createInternal(Annotation annotation) {
        Class<? extends Annotation> annotationType = annotation.annotationType();

        if (!annotationType.isAnnotationPresent(Condition.class)) {
            throw new RuntimeException("Condition annotation not found!");
        }
        return getOrCreateFluentCondition(annotationType.getAnnotation(Condition.class));
    }

    private FluentCondition getOrCreateFluentCondition(Condition condition) {
        if (registry.contains(condition.value())) {
            return registry.findByType(condition.value())
                    .orElseThrow(() -> new RuntimeException("FluentCondition not found!"));
        }
        return createFluentCondition(condition);
    }

    private FluentCondition createFluentCondition(Condition condition) {
        FluentCondition fluentCondition = BeanUtils.instantiateClass(condition.value());
        registry.addFluentCondition(fluentCondition);
        return fluentCondition;
    }
}
