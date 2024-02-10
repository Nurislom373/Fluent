package org.khasanof;

import jakarta.annotation.PostConstruct;
import org.khasanof.executors.determination.HandleStateFunction;
import org.khasanof.executors.processor.StateChainProcessor;
import org.khasanof.mediator.PerformMediator;
import org.khasanof.registry.processor.UpdateChainProcessorRegistryContainer;
import org.khasanof.service.interceptor.PreExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/8/2024 10:36 PM
 */
@Configuration
public class StateChainProcessorRegistryConfiguration {

    @Autowired
    private UpdateChainProcessorRegistryContainer registryContainer;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void registry() {
        registryContainer.addUpdateChainProcessor(new StateChainProcessor(applicationContext.getBean(PerformMediator.class),
                applicationContext, applicationContext.getBean(PreExecutionService.class), applicationContext.getBean(HandleStateFunction.class)));
    }

}
