package org.khasanof;

import org.khasanof.factories.executor.UpdateExecutorFactory;
import org.khasanof.factories.executor.SimulateExecutorServiceFactory;
import org.khasanof.service.ReinitializeFluentTemplateService;
import org.khasanof.service.processor.UpdateChainProcessorService;
import org.khasanof.verifier.DefaultFluentVerifier;
import org.khasanof.verifier.FluentVerifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:02 PM
 */
@Configuration
@ConditionalOnClass({
        ProxyFluentBotFactoryConfiguration.class,
        UpdateExecutorFactoryConfiguration.class,
        SimulateExecutorServiceFactoryConfiguration.class
})
public class FluentVerifierConfiguration {

    /**
     * @param updateExecutorFactory
     * @param serviceFactory
     * @param updateChainProcessorService
     * @param reinitializeFluentTemplateService
     * @return
     */
    @Bean
    public FluentVerifier fluentVerifier(UpdateExecutorFactory updateExecutorFactory,
                                         SimulateExecutorServiceFactory serviceFactory,
                                         UpdateChainProcessorService updateChainProcessorService,
                                         ReinitializeFluentTemplateService reinitializeFluentTemplateService) {

        return new DefaultFluentVerifier(updateExecutorFactory, serviceFactory, updateChainProcessorService, reinitializeFluentTemplateService);
    }
}
