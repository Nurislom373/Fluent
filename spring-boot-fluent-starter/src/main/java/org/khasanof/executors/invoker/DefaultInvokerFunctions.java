package org.khasanof.executors.invoker;

import jakarta.annotation.PostConstruct;
import org.khasanof.FluentBot;
import org.khasanof.annotation.exception.HandleException;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.condition.Condition;
import org.khasanof.enums.InvokerType;
import org.khasanof.executors.expression.ExpressionVariables;
import org.khasanof.model.InvokerMethod;
import org.khasanof.model.InvokerModel;
import org.khasanof.model.additional.checks.ACInvokerMethod;
import org.khasanof.model.additional.param.APAnnotationMap;
import org.khasanof.model.additional.param.APUpdateObject;
import org.khasanof.model.condition.MethodCondition;
import org.khasanof.utils.AnnotationUtils;
import org.khasanof.utils.UpdateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 8/11/2023 9:43 PM
 */
@Component
public class DefaultInvokerFunctions {

    private final InvokerFunctions functions;
    private final DefaultInvokerMatcher invokerMatcher;
    private final ApplicationContext applicationContext;
    private final ExpressionVariables matcher;

    public static final String EXCEPTION_NAME = "handleException";
    public static final String HANDLE_UPDATE = "handleUpdate";
    public static final String HANDLE_UPDATE_W_PROCESS_FL = "handleUpdateWithProcessFile";
    public static final String HANDLE_UPDATE_W_VAR_EXPRESSION = "handleUpdateWithVariableExpression";
    public static final String HANDLE_ANY_UPDATE = "handleAnyUpdate";
    public static final String HANDLE_STATE = "handleState";

    public DefaultInvokerFunctions(InvokerFunctionsImpl functions, DefaultInvokerMatcher invokerMatcher,
                                   ApplicationContext applicationContext, ExpressionVariables matcher) {
        this.matcher = matcher;
        this.functions = functions;
        this.invokerMatcher = invokerMatcher;
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    void afterPropertiesSet() {

        InvokerModel handleAny = InvokerModel.builder()
                .name(HANDLE_ANY_UPDATE)
                .type(InvokerType.METHOD)
                .condition((MethodCondition) (invokerMethod -> AnnotationUtils.hasAnnotation(invokerMethod.getMethod(),
                        HandleAny.class, false)))
                .methodParams(List.of(Update.class, AbsSender.class))
                .isInputSystem(false)
                .canBeNoParam(true)
                .build();
        functions.add(handleAny);

        InvokerModel handleMessageScopeVarExpression = InvokerModel.builder()
                .name(HANDLE_UPDATE_W_VAR_EXPRESSION)
                .type(InvokerType.METHOD)
                .condition((MethodCondition) (invokerMethod -> AnnotationUtils.hasAnnotation(invokerMethod.getMethod(),
                        HandleMessage.class, false)))
                .additionalParam((APAnnotationMap) (entry -> matcher.getMatchVariables(((HandleMessage)
                        entry.getValue()).value(), entry.getKey().getMessage().getText())))
                .additionalChecks((ACInvokerMethod) invokerMatcher::messageScopeEq)
                .methodParams(List.of(Update.class, AbsSender.class, Map.class))
                .isInputSystem(false)
                .canBeNoParam(false)
                .build();
        functions.add(handleMessageScopeVarExpression);

        InvokerModel handleException = InvokerModel.builder()
                .name(EXCEPTION_NAME)
                .type(InvokerType.METHOD)
                .condition((MethodCondition) (invokerMethod -> AnnotationUtils.hasAnnotation(invokerMethod.getMethod(),
                        HandleException.class, false)))
                .methodParams(List.of(Update.class, AbsSender.class, Throwable.class))
                .isInputSystem(true)
                .canBeNoParam(false)
                .build();
        functions.add(handleException);

        InvokerModel handleProcessFile = InvokerModel.builder()
                .name(HANDLE_UPDATE_W_PROCESS_FL)
                .type(InvokerType.METHOD)
                .condition((MethodCondition) (this::handleUpdateWithProcessFileMethodCondition))
                .additionalParam((APUpdateObject) (update ->
                        UpdateUtils.getInputStreamWithFileId(UpdateUtils.getFileId(update),
                                applicationContext.getBean(FluentBot.class))))
                .methodParams(List.of(Update.class, AbsSender.class, InputStream.class))
                .isInputSystem(false)
                .canBeNoParam(false)
                .build();
        functions.add(handleProcessFile);

        InvokerModel handleUpdates = InvokerModel.builder()
                .name(HANDLE_UPDATE)
                .type(InvokerType.METHOD)
                .condition((MethodCondition) (invokerMethod -> AnnotationUtils.hasAnnotation(invokerMethod.getMethod(),
                        ProcessUpdate.class, true)))
                .methodParams(List.of(Update.class, AbsSender.class))
                .isInputSystem(false)
                .canBeNoParam(false)
                .build();
        functions.add(handleUpdates);

    }

    private boolean handleUpdateWithProcessFileMethodCondition(InvokerMethod invokerMethod) {
        return Condition.orElse(() -> {
            return AnnotationUtils.hasAnnotation(invokerMethod.getMethod(), ProcessFile.class, true) &&
                    (invokerMethod.getMethod().getParameterCount() > 2);
        }, true, false);

    }

}
