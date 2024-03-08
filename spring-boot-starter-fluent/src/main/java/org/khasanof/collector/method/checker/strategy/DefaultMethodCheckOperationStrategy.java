package org.khasanof.collector.method.checker.strategy;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.constants.ParamConstants;
import org.khasanof.exceptions.InvalidParamsException;

import java.lang.reflect.Method;

import static org.khasanof.collector.method.checker.strategy.MethodCheckOperationUtils.*;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker.strategy
 * @since 1/21/2024 2:19 PM
 */
@Slf4j
public class DefaultMethodCheckOperationStrategy implements MethodCheckOperationStrategy {

    private final Class<?>[] MAIN_PARAMS = ParamConstants.DEFAULT_HANDLER_PARAM;

    @Override
    public boolean check(Method method) {
        checkMethodParameters(method);
        return true;
    }

    private void checkMethodParameters(Method method) {
        if (method.getParameterCount() < 0 || method.getParameterCount() > 1) {
            log.warn("method parameters are not declared correctly");
            throw new InvalidParamsException("There is an error in the method parameters with handle annotations!");
        }
        checkMethodParametersInternal(method);
    }

    private void checkMethodParametersInternal(Method method) {
        if (method.getParameterCount() != 0) {
            checkFalseThen(() -> MethodCheckOperationUtils.paramsTypeCheck(method.getParameterTypes(), MAIN_PARAMS),
                    new InvalidParamsException("There is an error in the method parameters with handle annotations!"));
        }
    }
}
