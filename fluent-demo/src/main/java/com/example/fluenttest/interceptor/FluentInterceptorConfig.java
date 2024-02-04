package com.example.fluenttest.interceptor;

import jakarta.annotation.PostConstruct;
import org.khasanof.registry.interceptor.FluentInterceptorRegistryContainer;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see com.example.fluenttest.interceptor
 * @since 2/4/2024 11:25 PM
 */
@Configuration
public class FluentInterceptorConfig {

    private final FluentInterceptorRegistryContainer registryContainer;

    public FluentInterceptorConfig(FluentInterceptorRegistryContainer registryContainer) {
        this.registryContainer = registryContainer;
    }

    @PostConstruct
    public void registerFluentInterceptor() {
        registryContainer.addFluentInterceptor(new SimpleFluentInterceptor());
    }
}
