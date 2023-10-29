package org.khasanof.executors;

import org.khasanof.MainHandler;
import org.khasanof.custom.ExecutorServiceFactory;
import org.khasanof.custom.UpdateTask;
import org.springframework.stereotype.Service;
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
@Service
public class CommonMainHandler implements MainHandler {

    private final ExecutorService executorService;
    private final UpdateExecutor executor;

    public CommonMainHandler(CommonUpdateExecutor executor, ExecutorServiceFactory serviceFactory) {
        this.executor = executor;
        this.executorService = serviceFactory.createExecutorService();
    }

    @Override
    public void process(Update update) {
        executorService.execute(new UpdateTask(update, () -> executor.execute(update)));
    }

}
