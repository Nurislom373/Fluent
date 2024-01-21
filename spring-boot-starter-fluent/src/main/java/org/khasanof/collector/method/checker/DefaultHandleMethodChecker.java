package org.khasanof.collector.method.checker;

import org.jetbrains.annotations.NotNull;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.collector.method.checker.strategy.MethodCheckOperationStrategy;
import org.khasanof.enums.ProcessType;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.factories.method.DefaultMethodCheckConditionFactory;
import org.khasanof.mediator.MethodCheckOperationStrategyMediator;
import org.khasanof.models.condition.MethodCondition;
import org.khasanof.utils.AnnotationUtils;
import org.khasanof.utils.ReflectionUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
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
@Component // TODO rewrite today!
public class DefaultHandleMethodChecker extends AbstractHandleMethodChecker {

    private final Set<Class<?>> classes = ReflectionUtils.getSubTypesSuperAnnotation(ProcessUpdate.class);
    private final DefaultMethodCheckConditionFactory conditionFactory;
    private final MethodCheckOperationStrategyMediator checkOperationStrategyMediator;

    public DefaultHandleMethodChecker(DefaultMethodCheckConditionFactory conditionFactory,
                                      MethodCheckOperationStrategyMediator checkOperationStrategyMediator) {

        this.conditionFactory = conditionFactory;
        this.checkOperationStrategyMediator = checkOperationStrategyMediator;
    }

    @Override
    public boolean check(Method method) {
        if (method.getAnnotations().length == 0 || method.getParameterCount() < 2) {
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
        if (classes.contains(annotation.annotationType()))
            matchCount.getAndIncrement();
    }

    @Override
    public Set<MethodCondition> conditions() {
        return conditionFactory.cachedCreate();
    }

    @Override
    public ProcessType processType() {
        return ProcessType.UPDATE;
    }
}
