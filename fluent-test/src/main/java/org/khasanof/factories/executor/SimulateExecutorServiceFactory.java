package org.khasanof.factories.executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author Nurislom
 * @see org.khasanof.factories.executor
 * @since 1/9/2024 10:22 PM
 */
public interface SimulateExecutorServiceFactory {

    ExecutorService create(CountDownLatch countDownLatch);

}
