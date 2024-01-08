package org.khasanof.config.custom;

import org.khasanof.custom.executor.DefaultExecutorServiceFactory;
import org.khasanof.custom.executor.ExecutorServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.custom
 * @since 1/8/2024 9:32 PM
 */
@Configuration
public class ExecutorServiceFactoryConfiguration {

    @Bean
    public ExecutorServiceFactory executorServiceFactory() {
        return new DefaultExecutorServiceFactory();
    }

}
