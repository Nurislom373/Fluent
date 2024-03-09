package org.khasanof.inline.perform;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.executors.execution.AbstractPerform;
import org.khasanof.feature.method.MethodType;
import org.khasanof.inline.type.InlineMethodType;
import org.khasanof.model.ChosenInlineQueryParam;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.inline.perform
 * @since 3/9/2024 12:58 PM
 */
@Component
public class ChosenInlineQueryPerform extends InlineQueryModePerform {

    @Override
    protected void internalFillParams(SimpleInvoker simpleInvoker, List<Object> params, List<Class<?>> methodParams) {
        methodParams.forEach(methodParam -> matchThenAdd(methodParam, params, simpleInvoker));
    }

    private void matchThenAdd(Class<?> paramType, List<Object> params, SimpleInvoker simpleInvoker) {
        if (Objects.equals(paramType, ChosenInlineQueryParam.class)) {
            params.add(simpleInvoker.getParams().get(InvokerParam.ADDITIONAL_PARAM));
            return;
        }
        if (Objects.equals(paramType, Update.class)) {
            params.add(FluentContextHolder.getCurrentFluentUpdate().getUpdate());
        }
    }

    @Override
    public MethodType getType() {
        return InlineMethodType.CHOSEN_INLINE_QUERY;
    }
}
