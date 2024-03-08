package org.khasanof.custom.executor;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.context.FluentUpdateContext;

import java.util.concurrent.ExecutorService;

/**
 * @author Nurislom
 * @see org.khasanof.custom
 * @since 10/28/2023 6:41 PM
 */
public class DefaultExecutorServiceFactory implements ExecutorServiceFactory {

    private final FluentUpdateContext fluentUpdateContext;

    public DefaultExecutorServiceFactory() {
        this.fluentUpdateContext = FluentContextHolder.getContext();
    }

    @Override
    public ExecutorService createExecutorService() {
        return new FluentThreadPoolExecutor(fluentUpdateContext);
    }

}
