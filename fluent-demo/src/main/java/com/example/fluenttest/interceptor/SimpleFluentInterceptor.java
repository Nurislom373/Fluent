package com.example.fluenttest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.feature.FluentInterceptor;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see com.example.fluenttest.interceptor
 * @since 2/4/2024 11:24 PM
 */
@Slf4j
public class SimpleFluentInterceptor implements FluentInterceptor {

    @Override
    public boolean preHandle(Update update) {
        log.info("FluentInterceptor handle update! : {}", update.getUpdateId());
        return FluentInterceptor.super.preHandle(update);
    }
}
