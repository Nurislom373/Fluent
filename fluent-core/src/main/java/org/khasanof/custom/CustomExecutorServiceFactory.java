package org.khasanof.custom;

import org.khasanof.context.FluentUpdateContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Nurislom
 * @see org.khasanof.custom
 * @since 10/28/2023 6:41 PM
 */
@Component
public class CustomExecutorServiceFactory implements ExecutorServiceFactory {

    private final FluentUpdateContext fluentUpdateContext;

    public CustomExecutorServiceFactory(FluentUpdateContext fluentUpdateContext) {
        this.fluentUpdateContext = fluentUpdateContext;
    }

    @Override
    public ExecutorService createExecutorService() {
        return new ThreadPoolExecutor(8, 16, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                if (r instanceof UpdateTask updateRunnable) {
                    fluentUpdateContext.addContext(t.getName(), updateRunnable.update());
                }
                super.beforeExecute(t, r);
            }

        };
    }

}
