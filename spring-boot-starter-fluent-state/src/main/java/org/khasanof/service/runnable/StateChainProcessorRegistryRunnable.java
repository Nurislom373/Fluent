package org.khasanof.service.runnable;

import org.khasanof.executors.determination.HandleStateFunction;
import org.khasanof.executors.processor.StateChainProcessor;
import org.khasanof.mediator.PerformMediator;
import org.khasanof.registry.processor.UpdateChainProcessorRegistryContainer;
import org.khasanof.registry.state.UserProceedStateRegistryContainer;
import org.khasanof.service.interceptor.PreExecutionService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.service.runnable
 * @since 2/29/2024 8:54 PM
 */
@Service
public class StateChainProcessorRegistryRunnable implements PostProcessor {

    private final PerformMediator performMediator;
    private final ApplicationContext applicationContext;
    private final HandleStateFunction handleStateFunction;
    private final PreExecutionService preExecutionService;
    private final UpdateChainProcessorRegistryContainer registryContainer;
    private final UserProceedStateRegistryContainer proceedStateRegistryContainer;

    public StateChainProcessorRegistryRunnable(PerformMediator performMediator,
                                               ApplicationContext applicationContext,
                                               HandleStateFunction handleStateFunction,
                                               PreExecutionService preExecutionService,
                                               UpdateChainProcessorRegistryContainer registryContainer,
                                               UserProceedStateRegistryContainer proceedStateRegistryContainer) {

        this.performMediator = performMediator;
        this.applicationContext = applicationContext;
        this.handleStateFunction = handleStateFunction;
        this.preExecutionService = preExecutionService;
        this.registryContainer = registryContainer;
        this.proceedStateRegistryContainer = proceedStateRegistryContainer;
    }

    @Override
    public void run() {
        registryContainer.addUpdateChainProcessor(new StateChainProcessor(performMediator, applicationContext,
                preExecutionService, handleStateFunction, proceedStateRegistryContainer));
    }
}
