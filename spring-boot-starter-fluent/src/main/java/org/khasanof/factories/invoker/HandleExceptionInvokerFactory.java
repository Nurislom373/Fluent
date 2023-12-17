package org.khasanof.factories.invoker;

import org.jetbrains.annotations.NotNull;
import org.khasanof.annotation.exception.HandleException;
import org.khasanof.enums.InvokerType;
import org.khasanof.models.Invoker;
import org.khasanof.models.condition.MethodCondition;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.factories.invoker
 * @since 12/17/2023 3:14 PM
 */
@Component(HandleExceptionInvokerFactory.NAME)
public class HandleExceptionInvokerFactory implements InvokerFactory {

    public static final String NAME = "handleExceptionInvokerFactory";
    public static final String HANDLE_EXCEPTION = "handleException";

    @Override
    public Invoker create() {
        return Invoker.builder()
                .name(HANDLE_EXCEPTION)
                .type(InvokerType.METHOD)
                .condition(getMethodCondition())
                .methodParams(List.of(Update.class, AbsSender.class, Throwable.class))
                .isInputSystem(true)
                .canBeNoParam(false)
                .build();
    }

    @NotNull
    private static MethodCondition getMethodCondition() {
        return invokerMethod -> AnnotationUtils.hasAnnotation(invokerMethod.getMethod(),
                HandleException.class, false);
    }
}
