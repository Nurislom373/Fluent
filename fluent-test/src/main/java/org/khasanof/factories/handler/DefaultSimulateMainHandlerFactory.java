package org.khasanof.factories.handler;

import org.khasanof.MainHandler;
import org.khasanof.executors.UpdateExecutor;
import org.khasanof.simulate.SimulateMainHandler;

import java.util.concurrent.ExecutorService;

/**
 * @author Nurislom
 * @see org.khasanof.factories.handler
 * @since 1/9/2024 9:56 PM
 */
public class DefaultSimulateMainHandlerFactory implements SimulateMainHandlerFactory {

    private final ExecutorService executorService;

    public DefaultSimulateMainHandlerFactory(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public MainHandler create(UpdateExecutor updateExecutor) {
        return new SimulateMainHandler(updateExecutor, executorService);
    }

}
