package org.khasanof.collector.method.checker.strategy;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.constants.ParamConstants;
import org.khasanof.exceptions.InvalidParamsException;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Method;
import java.util.Objects;

import static org.khasanof.collector.method.checker.strategy.MethodCheckOperationUtils.*;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker.strategy
 * @since 1/21/2024 2:23 PM
 */
@Slf4j
public class VarExpressionMethodCheckOperationStrategy implements MethodCheckOperationStrategy {

    private final Class<?>[] MAIN_PARAMS = ParamConstants.DEFAULT_HANDLER_PARAM;

    @Override
    public boolean check(Method method) {
        checkMethodParameters(method);
        return true;
    }

    private void checkMethodParameters(Method method) {
        String expression = getAnnotationValue(method);
        int count = countWordsInBraces(expression);

        if (methodParameterFirstUpdate(method)) {
            count += 1;
        }

        if (method.getParameterCount() > count) {
            log.warn("method parameters are not declared correctly!");
            throw new InvalidParamsException("There is an error in the method parameters with handle annotations!");
        }
    }

    private boolean methodParameterFirstUpdate(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        return Objects.equals(parameterTypes[0], Update.class);
    }

    private String getAnnotationValue(Method method) {
        return method.getAnnotation(HandleMessage.class).value();
    }
}
