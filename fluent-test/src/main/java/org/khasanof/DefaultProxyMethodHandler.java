package org.khasanof;

import org.khasanof.execute.ExecuteMethodCollector;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/8/2024 10:15 PM
 */
public class DefaultProxyMethodHandler implements ProxyMethodHandler {

    private final ExecuteMethodCollector executeMethodCollector;

    public DefaultProxyMethodHandler(ExecuteMethodCollector executeMethodCollector) {
        this.executeMethodCollector = executeMethodCollector;
    }

    @Override
    public boolean isExecuteMethod(Method method) {
        return executeMethodCollector.contains(method);
    }
}
