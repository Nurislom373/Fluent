package org.khasanof.registry.interceptor;

import org.khasanof.feature.interceptor.FluentInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.registry.interceptor
 * @since 2/4/2024 11:19 PM
 */
public class DefaultFluentInterceptorRegistryContainer implements FluentInterceptorRegistryContainer {

    private final List<FluentInterceptor> interceptors = new ArrayList<>();

    @Override
    public List<FluentInterceptor> getFluentInterceptors() {
        return this.interceptors;
    }

    @Override
    public void addFluentInterceptors(List<FluentInterceptor> interceptors) {
        if (Objects.nonNull(interceptors) && !interceptors.isEmpty()) {
            interceptors.forEach(this::addFluentInterceptor);
        }
    }

    @Override
    public void addFluentInterceptor(FluentInterceptor interceptor) {
        if (Objects.nonNull(interceptor)) {
            interceptors.add(interceptor);
        }
    }
}
