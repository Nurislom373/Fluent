package org.khasanof.collector.method.checker;

import org.khasanof.enums.ProcessType;
import org.khasanof.factories.method.DefaultMethodCheckConditionFactory;
import org.khasanof.mediator.MethodCheckOperationStrategyMediator;
import org.khasanof.models.condition.MethodGenericCondition;
import org.khasanof.registry.annotation.FluentAnnotationsRegistry;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/26/2023 9:44 PM
 */
@Component
public class DefaultHandleMethodChecker extends AbstractHandleMethodChecker {

    private final FluentAnnotationsRegistry fluentAnnotationsRegistry;
    private final DefaultMethodCheckConditionFactory conditionFactory;
    private final MethodCheckOperationStrategyMediator checkOperationStrategyMediator;

    public DefaultHandleMethodChecker(FluentAnnotationsRegistry fluentAnnotationsRegistry,
                                      DefaultMethodCheckConditionFactory conditionFactory,
                                      MethodCheckOperationStrategyMediator checkOperationStrategyMediator) {

        this.conditionFactory = conditionFactory;
        this.fluentAnnotationsRegistry = fluentAnnotationsRegistry;
        this.checkOperationStrategyMediator = checkOperationStrategyMediator;
    }

    @Override
    public boolean check(Method method) {
        if (method.getAnnotations().length == 0) {
            return false;
        }

        if (getMatchCount(method) != 1) {
            return false;
        }
        return checkOperationStrategyMediator.check(method);
    }

    private Integer getMatchCount(Method method) {
        AtomicInteger matchCount = new AtomicInteger();

        Arrays.stream(method.getAnnotations())
                .forEach(annotation -> checkAnnotationType(annotation, matchCount));
        return matchCount.get();
    }

    private void checkAnnotationType(Annotation annotation, AtomicInteger matchCount) {
        Set<Class<?>> annotations = fluentAnnotationsRegistry.getAnnotations();

        if (annotations.contains(annotation.annotationType())) {
            matchCount.getAndIncrement();
        }
    }

    @Override
    public Set<MethodGenericCondition> conditions() {
        return conditionFactory.cachedCreate();
    }

    @Override
    public ProcessType processType() {
        return ProcessType.UPDATE;
    }
}
