package org.khasanof.executors.execution;

import org.khasanof.models.invoker.SimpleInvoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.khasanof.utils.MethodUtils.tryAccessWhenMethodNotPublic;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 2/3/2024 4:11 PM
 */
public interface PerformHelper {

    /**
     *
     * @param simpleInvoker
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    static void invokeNoParamMethod(SimpleInvoker simpleInvoker) throws IllegalAccessException, InvocationTargetException {
        Method invokerMethod = simpleInvoker.getMethod();

        tryAccessWhenMethodNotPublic(invokerMethod);
        invokerMethod.invoke(simpleInvoker.getReference());
    }
}
