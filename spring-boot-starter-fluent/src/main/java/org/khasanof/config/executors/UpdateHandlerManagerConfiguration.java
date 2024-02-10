package org.khasanof.config.executors;

import org.khasanof.custom.executor.ExecutorServiceFactory;
import org.khasanof.executors.UpdateHandler;
import org.khasanof.executors.DefaultUpdateHandlerManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.executors
 * @since 2/10/2024 12:35 AM
 */
@Configuration
public class UpdateHandlerManagerConfiguration {

    /**
     *
     * @param updateHandler
     * @param executorServiceFactory
     * @return {@link DefaultUpdateHandlerManager} bean
     */
    @Bean
    public DefaultUpdateHandlerManager updateHandlerManager(UpdateHandler updateHandler, ExecutorServiceFactory executorServiceFactory) {
        return new DefaultUpdateHandlerManager(updateHandler, executorServiceFactory);
    }
}
