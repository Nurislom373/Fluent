package org.khasanof.custom.executor;

import java.util.concurrent.ExecutorService;

/**
 * @author Nurislom
 * @see org.khasanof.custom
 * @since 10/28/2023 7:21 PM
 */
public interface ExecutorServiceFactory {

    ExecutorService createExecutorService();

}
