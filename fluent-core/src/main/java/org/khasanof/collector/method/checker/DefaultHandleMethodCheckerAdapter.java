package org.khasanof.collector.method.checker;

import org.khasanof.models.condition.MethodCondition;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/26/2023 9:30 PM
 */
public class DefaultHandleMethodCheckerAdapter implements HandleMethodCheckerAdapter {

    private final Collection<HandleMethodChecker> methodCheckers = new CopyOnWriteArrayList<>();

    @Override
    public boolean check(Method method) {
        return methodCheckers.stream()
                .filter(handleMethodChecker -> isMatchConditions(handleMethodChecker.conditions(), method))
                .findFirst()
                .map(handleMethodChecker -> handleMethodChecker.check(method))
                .orElse(false);
    }

    public void setMethodCheckers(Collection<HandleMethodChecker> methodCheckers) {
        if (Objects.nonNull(methodCheckers) && !methodCheckers.isEmpty()) {
            this.methodCheckers.addAll(methodCheckers);
        }
    }

    private boolean isMatchConditions(Set<MethodCondition> conditions, Method method) {
        return conditions.stream()
                .allMatch(methodCondition -> methodCondition.match(method));
    }

}
