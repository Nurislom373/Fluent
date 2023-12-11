package org.khasanof.custom;

import org.jetbrains.annotations.NotNull;
import org.khasanof.context.FluentUpdateContext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Nurislom
 * @see org.khasanof.custom
 * @since 12/11/2023 9:02 PM
 */
public class FluentThreadPoolExecutor extends ThreadPoolExecutor {

    private final FluentUpdateContext updateContext;

    public FluentThreadPoolExecutor(FluentUpdateContext updateContext) {
        super(8, 16, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        this.updateContext = updateContext;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        if (r instanceof UpdateTask updateRunnable) {
            updateContext.addContext(t.getName(), updateRunnable.update());
        }
        super.beforeExecute(t, r);
    }

}
