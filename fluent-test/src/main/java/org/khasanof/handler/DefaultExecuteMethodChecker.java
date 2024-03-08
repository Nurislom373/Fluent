package org.khasanof.handler;

import org.khasanof.execute.ExecuteMethodCollector;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/8/2024 10:15 PM
 */
public class DefaultExecuteMethodChecker implements ExecuteMethodChecker {

    private final ExecuteMethodCollector executeMethodCollector;

    public DefaultExecuteMethodChecker(ExecuteMethodCollector executeMethodCollector) {
        this.executeMethodCollector = executeMethodCollector;
    }

    @Override
    public boolean isExecuteMethod(Method method) {
        return executeMethodCollector.contains(method);
    }
}
