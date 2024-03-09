package org.khasanof.inline.param;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.feature.HandleMethodExtraParam;
import org.khasanof.feature.method.MethodType;
import org.khasanof.inline.type.InlineMethodType;
import org.khasanof.model.ChosenInlineQueryParam;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;

/**
 * @author Nurislom
 * @see org.khasanof.inline.param
 * @since 3/9/2024 12:53 PM
 */
@Component
public class ChosenInlineQueryHandleMethodExtraParam implements HandleMethodExtraParam {

    @Override
    public void execute(SimpleInvoker simpleInvoker) {
        ChosenInlineQuery chosenInlineQuery = getChosenInlineQuery();
        ChosenInlineQueryParam param = new ChosenInlineQueryParam(chosenInlineQuery.getResultId(), chosenInlineQuery.getQuery());
        simpleInvoker.getParams().put(InvokerParam.ADDITIONAL_PARAM, param);
    }

    private ChosenInlineQuery getChosenInlineQuery() {
        return FluentContextHolder.getCurrentUpdate().getChosenInlineQuery();
    }

    @Override
    public MethodType methodType() {
        return InlineMethodType.CHOSEN_INLINE_QUERY;
    }
}
