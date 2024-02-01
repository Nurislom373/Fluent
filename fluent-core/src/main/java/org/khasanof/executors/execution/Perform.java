package org.khasanof.executors.execution;

import org.khasanof.enums.MethodType;
import org.khasanof.models.invoker.SimpleInvoker;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 1/27/2024 6:45 PM
 */
public interface Perform {

    void execute(SimpleInvoker simpleInvoker) throws InvocationTargetException, IllegalAccessException;

    MethodType getType();

}
