package org.khasanof.executors.execution;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.enums.MethodType;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 2/8/2024 10:41 PM
 */
@Component
public class PerformState extends AbstractPerform {

    @Override
    void fillParams(SimpleInvoker simpleInvoker, List<Object> params) {
        params.add(FluentContextHolder.getCurrentUpdate().getUpdate());
        params.add(simpleInvoker.getParams().get(InvokerParam.ADDITIONAL_PARAM));
    }

    @Override
    public MethodType getType() {
        return MethodType.STATE;
    }
}
