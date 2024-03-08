package org.khasanof;

import org.khasanof.factories.executor.DefaultUpdateExecutorFactory;
import org.khasanof.factories.executor.UpdateExecutorFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:07 PM
 */
@Configuration
public class UpdateExecutorFactoryConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public UpdateExecutorFactory updateExecutorFactory() {
        return new DefaultUpdateExecutorFactory();
    }
}
