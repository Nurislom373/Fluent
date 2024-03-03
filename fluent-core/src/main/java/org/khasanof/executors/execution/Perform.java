package org.khasanof.executors.execution;

import org.khasanof.feature.method.MethodType;
import org.khasanof.models.invoker.SimpleInvoker;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 1/27/2024 6:45 PM
 */
public interface Perform {

    /**
     *
     * @param simpleInvoker
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    void execute(SimpleInvoker simpleInvoker) throws InvocationTargetException, IllegalAccessException;

    /**
     *
     * @return
     */
    MethodType getType();
}
