package org.khasanof.inline.checker;

import org.khasanof.annotation.HandleChosenInlineQuery;
import org.khasanof.collector.method.checker.HandleMethodChecker;
import org.khasanof.models.condition.MethodGenericCondition;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.inline.checker
 * @since 3/9/2024 12:35 PM
 */
@Component
public class ChosenInlineHandleMethodChecker implements HandleMethodChecker {

    @Override
    public boolean check(Method method) {
        if (method.getAnnotations().length == 0) {
            return false;
        }
        if (method.getParameterCount() > 2) {
            throw new RuntimeException("InlineQuery method parameter count less than two!");
        }
        return true;
    }

    @Override
    public Set<MethodGenericCondition> conditions() {
        return Set.of(method -> method.isAnnotationPresent(HandleChosenInlineQuery.class));
    }
}
