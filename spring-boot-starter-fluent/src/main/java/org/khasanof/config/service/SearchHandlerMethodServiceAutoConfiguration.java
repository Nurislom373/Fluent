package org.khasanof.config.service;

import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.executors.matcher.CommonMatcherAdapter;
import org.khasanof.service.DefaultSearchHandlerMethodService;
import org.khasanof.service.SearchHandlerMethodService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.service
 * @since 1/16/2024 1:15 AM
 */
@Configuration
public class SearchHandlerMethodServiceAutoConfiguration {

    @Bean
    SearchHandlerMethodService searchHandlerMethodService(CommonMatcherAdapter commonMatcher, SimpleMethodContext simpleMethodContext) {
        return new DefaultSearchHandlerMethodService(commonMatcher, simpleMethodContext);
    }

}
