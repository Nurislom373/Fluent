package org.khasanof.config.executors.appropriate;

import org.khasanof.executors.appropriate.*;
import org.khasanof.registry.appropriate.AppropriateMethodRegistryContainer;
import org.khasanof.registry.appropriate.AppropriateTypeRegistryContainer;
import org.khasanof.registry.appropriate.DefaultAppropriateMethodRegistryContainer;
import org.khasanof.registry.appropriate.DefaultAppropriateTypeRegistryContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.executors.appropriate
 * @since 2/4/2024 10:21 PM
 */
@Configuration
public class AppropriateAutoConfiguration {

    @Bean
    public AppropriateMethodRegistryContainer appropriateMethodRegistryContainer() {
        return new DefaultAppropriateMethodRegistryContainer();
    }

    @Bean
    public AppropriateTypeRegistryContainer appropriateTypeRegistryContainer() {
        return new DefaultAppropriateTypeRegistryContainer();
    }

    @Bean
    @ConditionalOnBean(AppropriateTypeRegistryContainer.class)
    public AppropriateTypeService appropriateTypeService(AppropriateTypeRegistryContainer registryContainer) {
        return new DefaultAppropriateTypeService(registryContainer);
    }

    @Bean
    @ConditionalOnBean(AppropriateMethodRegistryContainer.class)
    public AppropriateMethodService appropriateMethodService(AppropriateMethodRegistryContainer registryContainer) {
        return new DefaultAppropriateMethodService(registryContainer);
    }
}
