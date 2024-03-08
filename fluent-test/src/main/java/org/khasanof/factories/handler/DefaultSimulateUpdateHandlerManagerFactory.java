package org.khasanof.factories.handler;

import org.khasanof.UpdateHandlerManager;
import org.khasanof.executors.UpdateHandler;
import org.khasanof.simulate.SimulateUpdateHandlerManager;

import java.util.concurrent.ExecutorService;

/**
 * @author Nurislom
 * @see org.khasanof.factories.handler
 * @since 1/9/2024 9:56 PM
 */
public class DefaultSimulateUpdateHandlerManagerFactory implements SimulateUpdateHandlerManagerFactory {

    private final ExecutorService executorService;

    public DefaultSimulateUpdateHandlerManagerFactory(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public UpdateHandlerManager create(UpdateHandler updateExecutor) {
        return new SimulateUpdateHandlerManager(updateExecutor, executorService);
    }
}
