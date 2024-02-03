package org.khasanof.executors.execution;

import org.khasanof.models.invoker.SimpleInvoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.khasanof.executors.execution.PerformHelper.invokeNoParamMethod;
import static org.khasanof.utils.MethodUtils.tryAccessWhenMethodNotPublic;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 2/3/2024 7:44 PM
 */
public abstract class AbstractPerform implements Perform {

    @Override
    public void execute(SimpleInvoker simpleInvoker) throws InvocationTargetException, IllegalAccessException {
        if (!simpleInvoker.hasMethodParams()) {
            invokeNoParamMethod(simpleInvoker);
            return;
        }
        invokeMethod(simpleInvoker);
    }

    private void invokeMethod(SimpleInvoker simpleInvoker) throws InvocationTargetException, IllegalAccessException {
        Method invokerMethod = simpleInvoker.getMethod();
        List<Object> params = new ArrayList<>();

        fillParams(simpleInvoker, params);

        Object[] paramsArray = params.toArray();
        tryAccessWhenMethodNotPublic(invokerMethod);
        invokerMethod.invoke(simpleInvoker.getReference(), paramsArray);
    }

    abstract void fillParams(SimpleInvoker simpleInvoker, List<Object> params);

}
