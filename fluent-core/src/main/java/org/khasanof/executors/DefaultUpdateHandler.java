package org.khasanof.executors;

import lombok.SneakyThrows;
import org.khasanof.service.processor.UpdateChainProcessorService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 24.06.2023 0:46
 * @version 1.0.0
 */
public class DefaultUpdateHandler implements UpdateHandler {

    private final UpdateChainProcessorService updateChainProcessorService;


    public DefaultUpdateHandler(UpdateChainProcessorService updateChainProcessorService) {
        this.updateChainProcessorService = updateChainProcessorService;
    }

    /**
     * Using the {@link UpdateChainProcessorService#process(Update)} method, update collects the matching methods.
     * iterates the collected methods one by one. If an error or something happens, the execution of methods is stopped.
     *
     * @param update from telegram is coming.
     */
    @Override
    @SneakyThrows
    public void execute(Update update) {
        updateChainProcessorService.process(update);
    }
}
