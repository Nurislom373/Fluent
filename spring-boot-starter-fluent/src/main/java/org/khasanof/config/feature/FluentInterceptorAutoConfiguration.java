package org.khasanof.config.feature;

import org.khasanof.registry.interceptor.DefaultFluentInterceptorRegistryContainer;
import org.khasanof.registry.interceptor.FluentInterceptorRegistryContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.feature
 * @since 2/4/2024 11:22 PM
 */
@Configuration
public class FluentInterceptorAutoConfiguration {

    /**
     * FluentInterceptorRegistryContainer use register interceptors
     *
     * @return {@link FluentInterceptorRegistryContainer}
     */
    @Bean
    public FluentInterceptorRegistryContainer fluentInterceptorRegistryContainer() {
        return new DefaultFluentInterceptorRegistryContainer();
    }
}
