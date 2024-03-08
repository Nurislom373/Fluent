package org.khasanof.executors;

import org.khasanof.UpdateHandlerManager;
import org.khasanof.custom.executor.ExecutorServiceFactory;
import org.khasanof.custom.task.UpdateTask;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 10:50
 * <br/>
 * Package: org.khasanof.core.handler
 */
public class DefaultUpdateHandlerManager implements UpdateHandlerManager {

    private final UpdateHandler executor;
    private final ExecutorService executorService;

    public DefaultUpdateHandlerManager(UpdateHandler executor, ExecutorServiceFactory serviceFactory) {
        this.executor = executor;
        this.executorService = serviceFactory.createExecutorService();
    }

    /**
     *
     * @param update
     */
    @Override
    public void process(Update update) {
        executorService.execute(new UpdateTask(update, () -> executor.execute(update)));
    }
}
