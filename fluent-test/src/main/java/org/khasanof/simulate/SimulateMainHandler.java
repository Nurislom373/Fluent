package org.khasanof.simulate;

import org.khasanof.MainHandler;
import org.khasanof.custom.task.UpdateTask;
import org.khasanof.executors.UpdateExecutor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;

/**
 * @author Nurislom
 * @see org.khasanof.simulate
 * @since 1/9/2024 9:57 PM
 */
public class SimulateMainHandler implements MainHandler {

    private final UpdateExecutor executor;
    private final ExecutorService executorService;

    public SimulateMainHandler(UpdateExecutor executor,
                               ExecutorService executorService) {

        this.executor = executor;
        this.executorService = executorService;
    }

    @Override
    public void process(Update update) {
        executorService.execute(new UpdateTask(update, () -> executor.execute(update)));
    }
}
