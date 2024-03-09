package org.khasanof.inline.perform;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.executors.execution.AbstractPerform;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.inline.perform
 * @since 3/9/2024 1:02 PM
 */
@SuppressWarnings({"unchecked"})
public abstract class InlineQueryModePerform extends AbstractPerform {

    @Override
    protected void fillParams(SimpleInvoker simpleInvoker, List<Object> params) {
        if (!simpleInvoker.getParams().containsKey(InvokerParam.ADDITIONAL_PARAM)) {
            params.add(FluentContextHolder.getCurrentFluentUpdate().getUpdate());
            return;
        }
        List<Class<?>> methodParams = (List<Class<?>>) simpleInvoker.getParams().get(InvokerParam.METHOD_PARAMETER_TYPES);
        internalFillParams(simpleInvoker, params, methodParams);
    }

    /**
     *
     * @param simpleInvoker
     * @param params
     * @param methodParams
     */
    protected abstract void internalFillParams(SimpleInvoker simpleInvoker, List<Object> params, List<Class<?>> methodParams);
}
