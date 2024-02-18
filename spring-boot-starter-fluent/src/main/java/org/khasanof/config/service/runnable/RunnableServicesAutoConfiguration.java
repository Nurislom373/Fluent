package org.khasanof.config.service.runnable;

import jakarta.annotation.PostConstruct;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.runnable.PostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author Nurislom
 * @see org.khasanof.config.service.runnable
 * @since 2/4/2024 10:24 PM
 */
@Configuration
public class RunnableServicesAutoConfiguration {

    private final FindBeansOfTypeService findBeansOfTypeService;

    public RunnableServicesAutoConfiguration(FindBeansOfTypeService findBeansOfTypeService) {
        this.findBeansOfTypeService = findBeansOfTypeService;
    }

    /**
     * PostProcessor Runner
     */
    @PostConstruct
    void afterPropertiesSet() {
        findBeansOfTypeService.findAllByList(PostProcessor.class)
                .forEach(Runnable::run);
    }
}
