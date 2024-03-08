package org.khasanof.collector.method.checker;

import org.khasanof.models.condition.MethodGenericCondition;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/26/2023 9:30 PM
 */
public class DefaultHandleMethodCheckerMediator implements HandleMethodCheckerMediator {

    private final Set<HandleMethodChecker> methodCheckers = new HashSet<>();

    @Override
    public boolean check(Method method) {
        return methodCheckers.stream()
                .filter(handleMethodChecker -> isMatchConditions(handleMethodChecker.conditions(), method))
                .findFirst()
                .map(handleMethodChecker -> handleMethodChecker.check(method))
                .orElse(false);
    }

    @Override
    public void addMethodCheckers(Set<HandleMethodChecker> methodCheckers) {
        if (Objects.nonNull(methodCheckers)) {
            this.methodCheckers.addAll(methodCheckers);
        }
    }

    @Override
    public void addMethodChecker(HandleMethodChecker handleMethodChecker) {
        if (Objects.nonNull(handleMethodChecker)) {
            this.methodCheckers.add(handleMethodChecker);
        }
    }

    private boolean isMatchConditions(Set<MethodGenericCondition> conditions, Method method) {
        return conditions.stream()
                .allMatch(methodCondition -> methodCondition.match(method));
    }
}
