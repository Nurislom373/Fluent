package org.khasanof.service.interceptor;

import org.khasanof.feature.FluentInterceptor;
import org.khasanof.service.FindBeansOfTypeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.service.interceptor
 * @since 2/3/2024 6:29 PM
 */
@Service
public class DefaultFluentInterceptorService implements FluentInterceptorService, InitializingBean {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final List<FluentInterceptor> fluentInterceptors = new ArrayList<>();

    public DefaultFluentInterceptorService(FindBeansOfTypeService findBeansOfTypeService) {
        this.findBeansOfTypeService = findBeansOfTypeService;
    }

    @Override
    public boolean intercept(Update update) {
        if (fluentInterceptors.isEmpty()) {
            return true;
        }
        return fluentInterceptors.stream()
                .allMatch(fluentInterceptor -> fluentInterceptor.preHandle(update));
    }

    @Override
    public void afterPropertiesSet() {
        fluentInterceptors.addAll(findBeansOfTypeService.findAllByList(FluentInterceptor.class));
    }
}
