package org.khasanof;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.factories.executor.DefaultSimulateExecutorServiceFactory;
import org.khasanof.factories.executor.SimulateExecutorServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 10:25 PM
 */
@Configuration
public class SimulateExecutorServiceFactoryConfiguration {

    @Bean
    public SimulateExecutorServiceFactory simulateExecutorServiceFactory() {
        return new DefaultSimulateExecutorServiceFactory(FluentContextHolder.getContext());
    }

}
