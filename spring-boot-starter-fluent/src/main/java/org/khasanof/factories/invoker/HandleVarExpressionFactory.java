package org.khasanof.factories.invoker;

import org.jetbrains.annotations.NotNull;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.enums.InvokerType;
import org.khasanof.enums.MatchScope;
import org.khasanof.executors.expression.ExpressionVariables;
import org.khasanof.executors.invoker.DefaultInvokerMatcher;
import org.khasanof.models.Invoker;
import org.khasanof.models.additional.checks.ACInvokerMethod;
import org.khasanof.models.additional.param.APAnnotationMap;
import org.khasanof.models.condition.SimpleInvokerCondition;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;
import java.util.Map;

import static org.khasanof.constants.FluentConstants.HIGH_ORDER;

/**
 * @author Nurislom
 * @see org.khasanof.factories.invoker
 * @since 12/17/2023 3:05 PM
 */
@Component(HandleVarExpressionFactory.NAME)
public class HandleVarExpressionFactory implements InvokerFactory {

    public static final String NAME = "handleUpdateVarExpressionFactory";
    public static final String HANDLE_UPDATE_VAR_EXPRESSION = "handleUpdateVariableExpression";
    private final DefaultInvokerMatcher invokerMatcher;
    private final ExpressionVariables matcher;

    public HandleVarExpressionFactory(DefaultInvokerMatcher invokerMatcher, ExpressionVariables matcher) {
        this.invokerMatcher = invokerMatcher;
        this.matcher = matcher;
    }

    @Override
    public Invoker create() {
        return Invoker.builder()
                .name(HANDLE_UPDATE_VAR_EXPRESSION)
                .type(InvokerType.METHOD)
                .condition(getMethodCondition())
                .additionalParam(getAdditionalParam())
                .additionalChecks(checkHandlerScope())
                .methodParams(List.of(Update.class, AbsSender.class, Map.class))
                .isInputSystem(false)
                .canBeNoParam(false)
                .build();
    }

    @Override
    public int getOrder() {
        return HIGH_ORDER;
    }

    @NotNull
    private ACInvokerMethod checkHandlerScope() {
        return simpleInvoker -> invokerMatcher.checkHandleMessageScope(simpleInvoker, MatchScope.VAR_EXPRESSION);
    }

    @NotNull
    private APAnnotationMap getAdditionalParam() {
        return entry -> matcher.getMatchVariables(((HandleMessage) entry.getValue()).value(), entry.getKey().getMessage().getText());
    }

    @NotNull
    private static SimpleInvokerCondition getMethodCondition() {
        return invokerMethod -> AnnotationUtils.hasAnnotation(invokerMethod.getMethod(), HandleMessage.class, false);
    }

}
