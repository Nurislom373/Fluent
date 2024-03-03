package org.khasanof.registry.interceptor;

import org.khasanof.feature.interceptor.FluentInterceptor;

import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.registry.interceptor
 * @since 2/4/2024 11:19 PM
 */
public class DefaultFluentInterceptorRegistryContainer implements FluentInterceptorRegistryContainer {

    private final Set<FluentInterceptor> interceptors = new HashSet<>();

    @Override
    public Set<FluentInterceptor> getFluentInterceptors() {
        return this.interceptors;
    }

    @Override
    public void addFluentInterceptors(Set<FluentInterceptor> interceptors) {
        if (Objects.nonNull(interceptors)) {
            this.interceptors.addAll(interceptors);
        }
    }

    @Override
    public void addFluentInterceptor(FluentInterceptor interceptor) {
        if (Objects.nonNull(interceptor)) {
            interceptors.add(interceptor);
        }
    }
}
