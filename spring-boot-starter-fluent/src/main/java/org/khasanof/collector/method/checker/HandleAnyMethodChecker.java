package org.khasanof.collector.method.checker;

import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.enums.ProcessType;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.factories.method.HandleAnyMethodCheckConditionFactory;
import org.khasanof.models.condition.MethodCondition;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/27/2023 10:04 PM
 */
@Component
public class HandleAnyMethodChecker extends AbstractHandleMethodChecker {

    private final HandleAnyMethodCheckConditionFactory conditionFactory;

    public HandleAnyMethodChecker(HandleAnyMethodCheckConditionFactory conditionFactory) {
        this.conditionFactory = conditionFactory;
    }

    @Override
    public boolean check(Method method) {
        if (method.getAnnotations().length == 0) {
            return false;
        }
        return internalCheck(method);
    }

    private boolean internalCheck(Method method) {
        long matchCount = Arrays.stream(method.getAnnotations())
                .filter(annotation -> HandleAny.class.equals(annotation.annotationType()))
                .count();

        checkMethodParameters(method);
        return matchCount == 1;
    }

    private void checkMethodParameters(Method method) {
        int parameterCount = method.getParameterCount();

        if (!(parameterCount == 1 || parameterCount == 0)) {
            throw new InvalidParamsException("Invalid parameter handleAny!");
        }
        checkParameterTypes(method, parameterCount);
    }

    private void checkParameterTypes(Method method, int parameterCount) {
        if (parameterCount != 0) {
            if (!paramsTypeCheckV2(method.getParameterTypes(), MAIN_PARAMS)) {
                throw new InvalidParamsException("There is an error in the method parameters with handle annotations!");
            }
        }
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
