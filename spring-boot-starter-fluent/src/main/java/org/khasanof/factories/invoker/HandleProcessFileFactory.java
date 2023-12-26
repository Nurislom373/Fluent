package org.khasanof.factories.invoker;

import org.jetbrains.annotations.NotNull;
import org.khasanof.FluentBot;
import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.condition.Condition;
import org.khasanof.enums.InvokerType;
import org.khasanof.models.Invoker;
import org.khasanof.models.additional.param.APUpdateObject;
import org.khasanof.models.condition.SimpleInvokerCondition;
import org.khasanof.utils.AnnotationUtils;
import org.khasanof.utils.UpdateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.io.InputStream;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.factories.invoker
 * @since 12/17/2023 3:15 PM
 */
@Component(HandleProcessFileFactory.NAME)
public class HandleProcessFileFactory implements InvokerFactory {

    public static final String NAME = "handleProcessFileFactory";
    public static final String HANDLE_UPDATE_PROCESS_FILE = "handleUpdateWithProcessFile";
    private final ApplicationContext applicationContext;

    public HandleProcessFileFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Invoker create() {
        return Invoker.builder()
                .name(HANDLE_UPDATE_PROCESS_FILE)
                .type(InvokerType.METHOD)
                .condition(getMethodCondition())
                .additionalParam(getAdditionalParam())
                .methodParams(List.of(Update.class, AbsSender.class, InputStream.class))
                .isInputSystem(false)
                .canBeNoParam(false)
                .build();
    }

    @NotNull
    private APUpdateObject getAdditionalParam() {
        return update -> UpdateUtils.getInputStreamWithFileId(UpdateUtils.getFileId(update),
                        applicationContext.getBean(FluentBot.class));
    }

    @NotNull
    private static SimpleInvokerCondition getMethodCondition() {
        return simpleInvoker -> Condition.orElse(() -> AnnotationUtils.hasAnnotation(simpleInvoker.getMethod(), ProcessFile.class, true)
                && (simpleInvoker.getMethod().getParameterCount() > 2), true, false);
    }

}
