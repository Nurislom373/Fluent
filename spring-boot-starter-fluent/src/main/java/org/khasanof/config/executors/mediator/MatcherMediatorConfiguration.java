package org.khasanof.config.executors.mediator;

import org.khasanof.executors.matcher.CommonMatcherMediator;
import org.khasanof.service.FindBeansOfTypeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.executors.mediator
 * @since 3/3/2024 7:59 PM
 */
@Configuration
public class MatcherMediatorConfiguration {

    /**
     *
     * @param findBeansOfTypeService
     * @return
     */
    @Bean
    public CommonMatcherMediator commonMatcherMediator(FindBeansOfTypeService findBeansOfTypeService) {
        return new CommonMatcherMediator(findBeansOfTypeService);
    }
}
