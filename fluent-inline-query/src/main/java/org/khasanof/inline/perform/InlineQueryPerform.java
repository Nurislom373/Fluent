package org.khasanof.inline.perform;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.feature.method.MethodType;
import org.khasanof.inline.type.InlineMethodType;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.inline.perform
 * @since 3/3/2024 6:05 PM
 */
@Component
public class InlineQueryPerform extends InlineQueryModePerform {

    @Override
    protected void internalFillParams(SimpleInvoker simpleInvoker, List<Object> params, List<Class<?>> methodParams) {
        methodParams.forEach(methodParam -> matchThenAdd(methodParam, params, simpleInvoker));
    }

    private void matchThenAdd(Class<?> paramType, List<Object> params, SimpleInvoker simpleInvoker) {
        if (Objects.equals(paramType, String.class)) {
            params.add(simpleInvoker.getParams().get(InvokerParam.ADDITIONAL_PARAM));
            return;
        }
        if (Objects.equals(paramType, Update.class)) {
            params.add(FluentContextHolder.getCurrentFluentUpdate().getUpdate());
        }
    }

    @Override
    public MethodType getType() {
        return InlineMethodType.INLINE_QUERY;
    }
}
