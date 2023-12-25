package org.khasanof.factories.invoker;

import org.jetbrains.annotations.NotNull;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.enums.InvokerType;
import org.khasanof.models.Invoker;
import org.khasanof.models.condition.MethodCondition;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Nurislom
 * @see org.khasanof.factories.invoker
 * @since 12/17/2023 3:03 PM
 */
@Component(HandleAnyInvokerFactory.NAME)
public class HandleAnyInvokerFactory implements InvokerFactory {

    public static final String NAME = "handleAnyInvokerFactory";
    public static final String HANDLE_ANY_UPDATE = "handleAnyUpdate";

    @Override
    public Invoker create() {
        return Invoker.builder()
                .name(HANDLE_ANY_UPDATE)
                .type(InvokerType.METHOD)
                .condition(getMethodCondition())
                .methodParams(List.of(Update.class, AbsSender.class))
                .isInputSystem(false)
                .canBeNoParam(true)
                .build();
    }

    @NotNull
    private static MethodCondition getMethodCondition() {
        return invokerMethod -> AnnotationUtils.hasAnnotation(invokerMethod.getMethod(),
                HandleAny.class, false);
    }

}
