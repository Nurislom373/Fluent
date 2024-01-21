package org.khasanof.executors.execution;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.event.ExecutionMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.utils.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The {@link AbstractExecution} class is used to declare the methods and variables needed
 * by the classes of the {@link Execution} interface. And in this class there are also standard execution methods.
 *
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 8/20/2023 8:30 PM
 */
public abstract class AbstractExecution implements Execution {

    /**
     * The method is the method that calls the simple methods.
     *
     * @param executionMethod Which method should be called in the {@link ExecutionMethod} class comes in and is called.
     */
    protected void defaultExecution(ExecutionMethod executionMethod) throws InvocationTargetException, IllegalAccessException {
        SimpleInvoker simpleInvoker = executionMethod.getInvokerModel().getInvokerReference();
        getInvokerMethod(simpleInvoker).invoke(simpleInvoker.getReference(), methodParameterSorter(executionMethod, simpleInvoker));
    }

    protected static Object[] methodParameterSorter(ExecutionMethod executionMethod, SimpleInvoker simpleInvoker) {
        return MethodUtils.sorterV2(executionMethod.getInvokerModel().getArgs(), getInvokerMethod(simpleInvoker).getParameterTypes());
    }

    protected static Method getInvokerMethod(SimpleInvoker simpleInvoker) {
        return simpleInvoker.getMethod();
    }

    @Override
    public AdditionalParamType getType() {
        return null;
    }
}
