package org.khasanof.config.executors.processor;

import org.khasanof.executors.determination.DeterminationHandleAnyFunction;
import org.khasanof.executors.determination.DeterminationHandleUpdateFunction;
import org.khasanof.executors.processor.*;
import org.khasanof.factories.processor.DefaultUpdateChainProcessorFactory;
import org.khasanof.factories.processor.UpdateChainProcessorFactory;
import org.khasanof.mediator.PerformMediator;
import org.khasanof.registry.processor.DefaultUpdateChainProcessorRegistryContainer;
import org.khasanof.registry.processor.UpdateChainProcessorRegistryContainer;
import org.khasanof.service.exception.DefaultExceptionResolverService;
import org.khasanof.service.interceptor.DefaultPreExecutionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.config.executors.processor
 * @since 2/1/2024 11:30 PM
 */
@Configuration
public class UpdateChainProcessorAutoConfiguration {

    @Bean
    public UpdateChainProcessorRegistryContainer updateChainProcessorRegistryContainer() {
        return new DefaultUpdateChainProcessorRegistryContainer();
    }

    @Bean
    @ConditionalOnBean(UpdateChainProcessorRegistryContainer.class)
    public UpdateChainProcessorFactory updateChainProcessorFactory(UpdateChainProcessorRegistryContainer registryContainer) {
        return new DefaultUpdateChainProcessorFactory(registryContainer);
    }

    public static class ChainProcessorBuilder {

        private final ApplicationContext applicationContext;

        public ChainProcessorBuilder(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public List<AbstractUpdateChainProcessor> processors() {
            return List.of(
                    new ExceptionChainProcessor(applicationContext.getBean(DefaultExceptionResolverService.class)),

                    new DefaultChainProcessor(applicationContext.getBean(PerformMediator.class), applicationContext,
                            applicationContext.getBean(DefaultPreExecutionService.class),
                            applicationContext.getBean(DeterminationHandleUpdateFunction.class)),

                    new HandleAnyChainProcessor(applicationContext.getBean(PerformMediator.class), applicationContext,
                            applicationContext.getBean(DeterminationHandleAnyFunction.class))
            );
        }
    }

}
