package org.khasanof.factories.invoker;

import org.jetbrains.annotations.NotNull;
import org.khasanof.annotation.process.ProcessUpdate;
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
 * @since 12/17/2023 3:20 PM
 */
@Component(HandleUpdateInvokerFactory.NAME)
public class HandleUpdateInvokerFactory implements InvokerFactory {

    public static final String NAME = "handleUpdateInvokerFactory";
    public static final String HANDLE_UPDATE = "handleUpdate";

    @Override
    public Invoker create() {
        return Invoker.builder()
                .name(HANDLE_UPDATE)
                .type(InvokerType.METHOD)
                .condition(getMethodCondition())
                .methodParams(List.of(Update.class, AbsSender.class))
                .isInputSystem(false)
                .canBeNoParam(false)
                .build();
    }

    @NotNull
    private static MethodCondition getMethodCondition() {
        return invokerMethod -> AnnotationUtils.hasAnnotation(invokerMethod.getMethod(), ProcessUpdate.class, true);
    }

}
