package org.khasanof.execute;

import org.khasanof.method.ExecuteMethodReflect;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/8/2024 10:03 PM
 */
public class DefaultExecuteMethodDataRegistry {

    private final ExecuteMethodReflect executeMethodReflect;
    private final ExecuteMethodCollector executeMethodCollector;

    public DefaultExecuteMethodDataRegistry(ExecuteMethodCollector executeMethodCollector, ExecuteMethodReflect executeMethodReflect) {
        this.executeMethodCollector = executeMethodCollector;
        this.executeMethodReflect = executeMethodReflect;
    }

    public void addDefaultMethods() {
        executeMethodReflect.getMethodWithString()
                .forEach(executeMethodCollector::addMethod);
    }

}
