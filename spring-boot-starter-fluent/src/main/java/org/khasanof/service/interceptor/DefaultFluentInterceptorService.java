package org.khasanof.service.interceptor;

import org.khasanof.feature.interceptor.FluentInterceptor;
import org.khasanof.registry.interceptor.FluentInterceptorRegistryContainer;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.service.interceptor
 * @since 2/3/2024 6:29 PM
 */
@Service
public class DefaultFluentInterceptorService implements FluentInterceptorService {

    private final FluentInterceptorRegistryContainer registryContainer;

    public DefaultFluentInterceptorService(FluentInterceptorRegistryContainer registryContainer) {
        this.registryContainer = registryContainer;
    }

    @Override
    public boolean preIntercept(Update update) {
        return getInterceptors()
                .stream()
                .allMatch(fluentInterceptor -> fluentInterceptor.preHandle(update));
    }

    @Override
    public void postIntercept(Update update) {
        getInterceptors()
                .forEach(fluentInterceptor -> fluentInterceptor.postHandle(update));
    }

    private List<FluentInterceptor> getInterceptors() {
        return registryContainer.getFluentInterceptors();
    }
}
