package org.khasanof.collector.method.checker.strategy;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.constants.ParamConstants;
import org.khasanof.exceptions.InvalidParamsException;

import java.io.InputStream;
import java.lang.reflect.Method;

import static org.khasanof.collector.method.checker.strategy.MethodCheckOperationUtils.checkFalseThen;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker.strategy
 * @since 1/21/2024 2:23 PM
 */
@Slf4j
public class ProcessFileMethodCheckOperationStrategy implements MethodCheckOperationStrategy {

    private final Class<?>[] MAIN_PARAMS = ParamConstants.DEFAULT_HANDLER_PARAM;

    @Override
    public boolean check(Method method) {
        checkMethodParameters(method);
        return true;
    }

    private void checkMethodParameters(Method method) {
        if (method.getParameterCount() < 0 || method.getParameterCount() > 2) {
            log.warn("method parameters are not declared correctly");
            throw new InvalidParamsException("There is an error in the method parameters with handle annotations!");
        }
        checkMethodParametersInternal(method);
    }

    private void checkMethodParametersInternal(Method method) {
        Class<?>[] params = MAIN_PARAMS;

        if (method.getParameterCount() == 2) {
            params = new Class[MAIN_PARAMS.length + 1];

            System.arraycopy(MAIN_PARAMS, 0, params, 0, MAIN_PARAMS.length);
            params[params.length - 1] = InputStream.class;
        }
        checkMethodParametersInternal(method, params);
    }

    private void checkMethodParametersInternal(Method method, Class<?>[] params) {
        if (method.getParameterCount() != 0) {
            checkFalseThen(() -> MethodCheckOperationUtils.paramsTypeCheck(method.getParameterTypes(), params),
                    new InvalidParamsException("There is an error in the method parameters with handle annotations!"));
        }
    }
}
