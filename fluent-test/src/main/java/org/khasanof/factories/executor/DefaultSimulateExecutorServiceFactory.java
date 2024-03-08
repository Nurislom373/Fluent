package org.khasanof.factories.executor;

import org.khasanof.context.FluentUpdateContext;
import org.khasanof.custom.CountedFluentThreadPoolExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author Nurislom
 * @see org.khasanof.factories.executor
 * @since 1/9/2024 10:24 PM
 */
public class DefaultSimulateExecutorServiceFactory implements SimulateExecutorServiceFactory {

    private final FluentUpdateContext fluentUpdateContext;

    public DefaultSimulateExecutorServiceFactory(FluentUpdateContext fluentUpdateContext) {
        this.fluentUpdateContext = fluentUpdateContext;
    }

    @Override
    public ExecutorService create(CountDownLatch countDownLatch) {
        return new CountedFluentThreadPoolExecutor(fluentUpdateContext, countDownLatch);
    }
}
