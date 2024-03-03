package org.khasanof.registry.interceptor;

import org.khasanof.feature.interceptor.FluentInterceptor;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.registry.interceptor
 * @since 2/4/2024 11:18 PM
 */
public interface FluentInterceptorRegistryContainer {

    /**
     *
     * @return
     */
    Set<FluentInterceptor> getFluentInterceptors();

    /**
     *
     * @param interceptors
     */
    void addFluentInterceptors(Set<FluentInterceptor> interceptors);

    /**
     *
     * @param interceptor
     */
    void addFluentInterceptor(FluentInterceptor interceptor);
}
