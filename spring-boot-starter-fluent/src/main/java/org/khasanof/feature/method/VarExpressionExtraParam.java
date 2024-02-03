package org.khasanof.feature.method;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.context.FluentUpdate;
import org.khasanof.enums.MethodType;
import org.khasanof.executors.expression.ExpressionVariables;
import org.khasanof.feature.HandleMethodExtraParam;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.feature.method
 * @since 2/1/2024 10:32 PM
 */
@Component
public class VarExpressionExtraParam implements HandleMethodExtraParam {

    private final ExpressionVariables matcher;

    public VarExpressionExtraParam(ExpressionVariables matcher) {
        this.matcher = matcher;
    }

    @Override
    public void execute(SimpleInvoker simpleInvoker) {
        FluentUpdate currentUpdate = FluentContextHolder.getCurrentUpdate();
        Map<String, String> matchVariables = getMatchVariables(simpleInvoker, currentUpdate);
        simpleInvoker.getParams().put(InvokerParam.ADDITIONAL_PARAM, matchVariables);
    }

    private Map<String, String> getMatchVariables(SimpleInvoker simpleInvoker, FluentUpdate currentUpdate) {
        return matcher.getMatchVariables(((HandleMessage) simpleInvoker.getParams().get(InvokerParam.ANNOTATION)).value(),
                currentUpdate.getUpdate().getMessage().getText());
    }

    @Override
    public MethodType methodType() {
        return MethodType.VAR_EXPRESSION;
    }
}
