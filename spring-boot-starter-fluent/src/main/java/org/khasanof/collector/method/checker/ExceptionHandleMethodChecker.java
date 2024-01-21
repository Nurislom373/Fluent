package org.khasanof.collector.method.checker;

import org.khasanof.annotation.exception.HandleException;
import org.khasanof.enums.ProcessType;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.factories.method.ExceptionMethodCheckConditionFactory;
import org.khasanof.models.condition.MethodCondition;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/27/2023 10:24 PM
 */
@Component
public class ExceptionHandleMethodChecker extends AbstractHandleMethodChecker {

    private final List<Class<?>> validTypes = List.of(Throwable.class, Update.class, AbsSender.class);
    private final ExceptionMethodCheckConditionFactory conditionFactory;

    public ExceptionHandleMethodChecker(ExceptionMethodCheckConditionFactory conditionFactory) {
        this.conditionFactory = conditionFactory;
    }

    @Override
    public boolean check(Method method) {
        boolean validParams, validAnnotation;
        if (method.getAnnotations().length == 0) {
            return false;
        }

        checkParametersCount(method.getParameterTypes());
        validAnnotation = hasAnnotation(method, HandleException.class);
        validParams = allMatchParameter(method.getParameterTypes());

        checkValidAnnotationAndParams(validParams, validAnnotation);
        return validParams && validAnnotation;
    }

    private void checkValidAnnotationAndParams(boolean validParams, boolean validAnnotation) {
        if (!validParams && validAnnotation) {
            throw new InvalidParamsException("Exception handler method invalid parameters!");
        }
    }

    private void checkParametersCount(Class<?>[] parameterTypes) {
        if (parameterTypes.length < 1 || parameterTypes.length > 3) {
            throw new InvalidParamsException("Exception handler method invalid parameters!");
        }
    }

    private boolean allMatchParameter(Class<?>[] classes) {
        return Arrays.stream(classes).allMatch(clazz -> validTypes.stream()
                .anyMatch(valid -> valid.equals(clazz) || valid.isAssignableFrom(clazz)));
    }

    @Override
    public Set<MethodCondition> conditions() {
        return conditionFactory.cachedCreate();
    }

    @Override
    public ProcessType processType() {
        return ProcessType.BOTH;
    }
}
