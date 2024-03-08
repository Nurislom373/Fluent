package org.khasanof.config.service;

import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.executors.matcher.CommonMatcherMediator;
import org.khasanof.service.search.DefaultSearchHandlerMethodService;
import org.khasanof.service.search.SearchHandlerMethodService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.service
 * @since 1/16/2024 1:15 AM
 */
@Configuration
public class SearchHandlerMethodServiceAutoConfiguration {

    /**
     *
     * @param commonMatcher
     * @param simpleMethodContext
     * @return {@link SearchHandlerMethodService} bean
     */
    @Bean
    public SearchHandlerMethodService searchHandlerMethodService(CommonMatcherMediator commonMatcher,
                                                                 SimpleMethodContext simpleMethodContext) {

        return new DefaultSearchHandlerMethodService(commonMatcher, simpleMethodContext);
    }
}
