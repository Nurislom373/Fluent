package org.khasanof.registry.interceptor;

import org.khasanof.feature.interceptor.FluentInterceptor;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.registry.interceptor
 * @since 2/4/2024 11:18 PM
 */
public interface FluentInterceptorRegistryContainer {

    List<FluentInterceptor> getFluentInterceptors();

    void addFluentInterceptors(List<FluentInterceptor> interceptors);

    void addFluentInterceptor(FluentInterceptor interceptor);

}
