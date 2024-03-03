package org.khasanof.executors.execution;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 2/3/2024 7:07 PM
 */
@Component
public class ExceptionPerform extends AbstractPerform {

    @Override
    protected void fillParams(SimpleInvoker simpleInvoker, List<Object> params) {
        Arrays.stream(simpleInvoker.getMethod().getParameterTypes())
                .forEach(parameterType -> matchThenAdd(simpleInvoker, parameterType, params));
    }

    private void matchThenAdd(SimpleInvoker simpleInvoker, Class<?> parameterType, List<Object> params) {
        if (Objects.equals(parameterType, Update.class)) {
            params.add(FluentContextHolder.getCurrentFluentUpdate().getUpdate());
            return;
        }

        if (Objects.equals(parameterType, Throwable.class) || Throwable.class.isAssignableFrom(parameterType)) {
            params.add(simpleInvoker.getParams().get(InvokerParam.ADDITIONAL_PARAM));
        }
    }

    @Override
    public DefaultMethodType getType() {
        return DefaultMethodType.EXCEPTION_HANDLER;
    }
}
