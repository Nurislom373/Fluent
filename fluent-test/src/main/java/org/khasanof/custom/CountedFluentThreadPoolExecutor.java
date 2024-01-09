package org.khasanof.custom;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.context.FluentUpdateContext;
import org.khasanof.custom.executor.FluentThreadPoolExecutor;

import java.util.concurrent.CountDownLatch;

/**
 * @author Nurislom
 * @see org.khasanof.custom
 * @since 1/9/2024 10:17 PM
 */
@Slf4j
public class CountedFluentThreadPoolExecutor extends FluentThreadPoolExecutor {

    private final CountDownLatch countDownLatch;

    public CountedFluentThreadPoolExecutor(FluentUpdateContext updateContext,
                                           CountDownLatch countDownLatch) {

        super(updateContext);
        this.countDownLatch = countDownLatch;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        log.info("############################## Counted Down ##############################");
        countDownLatch.countDown();
        super.afterExecute(r, t);
    }
}
