package org.khasanof.collector.method.checker.strategy;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.exceptions.InvalidParamsException;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.khasanof.collector.method.checker.strategy.MethodCheckOperationUtils.checkFalseThen;
import static org.khasanof.constants.ParamConstants.PROCESS_FILE_PARAMS;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker.strategy
 * @since 1/21/2024 2:23 PM
 */
@Slf4j
public class ProcessFileMethodCheckOperationStrategy implements MethodCheckOperationStrategy {

    @Override
    public boolean check(Method method) {
        checkMethodParameters(method);
        return true;
    }

    private void checkMethodParameters(Method method) {
        if (method.getParameterCount() > 2) {
            log.warn("method parameters are not declared correctly");
            throw new InvalidParamsException("There is an error in the method parameters with handle annotations!");
        }
        checkMethodParametersInternal(method);
    }

    private void checkMethodParametersInternal(Method method) {
        if (method.getParameterCount() == 0) {
            return;
        }
        checkFalseThen(() -> isAllMatch(method), new InvalidParamsException("There is an error in the method parameters with handle annotations!"));
    }

    private boolean isAllMatch(Method method) {
        return Arrays.stream(method.getParameterTypes())
                .allMatch(PROCESS_FILE_PARAMS::contains);
    }
}
