package org.khasanof.executors.execution;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.khasanof.executors.execution.PerformHelper.invokeNoParamMethod;
import static org.khasanof.utils.MethodUtils.tryAccessWhenMethodNotPublic;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 1/27/2024 6:45 PM
 */
@Component
public class DefaultPerform implements Perform {

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

        tryAccessWhenMethodNotPublic(invokerMethod);
        invokerMethod.invoke(simpleInvoker.getReference(), FluentContextHolder.getCurrentUpdate().getUpdate());
    }

    @Override
    public DefaultMethodType getType() {
        return DefaultMethodType.DEFAULT;
    }
}
