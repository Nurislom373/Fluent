package org.khasanof.executors;

import org.khasanof.MainHandler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private final CommonUpdateExecutor executor;

    public CommonMainHandler(CommonUpdateExecutor executor) {
        this.executor = executor;
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void process(Update update) {
        executorService.execute(() -> executor.executeV2(update));
    }

}
