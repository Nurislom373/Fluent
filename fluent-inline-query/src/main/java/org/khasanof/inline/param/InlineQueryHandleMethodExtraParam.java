package org.khasanof.inline.param;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.feature.HandleMethodExtraParam;
import org.khasanof.feature.method.MethodType;
import org.khasanof.inline.type.InlineMethodType;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.inline.param
 * @since 3/3/2024 6:02 PM
 */
@Component
public class InlineQueryHandleMethodExtraParam implements HandleMethodExtraParam {

    @Override
    public void execute(SimpleInvoker simpleInvoker) {
        simpleInvoker.getParams().put(InvokerParam.ADDITIONAL_PARAM, getQuery());
    }

    private String getQuery() {
        return FluentContextHolder.getCurrentUpdate().getInlineQuery().getQuery();
    }

    @Override
    public MethodType methodType() {
        return InlineMethodType.INLINE_QUERY;
    }
}
