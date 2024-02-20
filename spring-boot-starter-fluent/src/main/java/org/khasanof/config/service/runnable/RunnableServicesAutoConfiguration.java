package org.khasanof.config.service.runnable;

import org.khasanof.collector.AssembleMethods;
import org.khasanof.constants.FluentConstants;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.runnable.PostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.service.runnable
 * @since 2/4/2024 10:24 PM
 */
@Configuration
public class RunnableServicesAutoConfiguration implements AssembleMethods {

    private final FindBeansOfTypeService findBeansOfTypeService;

    public RunnableServicesAutoConfiguration(FindBeansOfTypeService findBeansOfTypeService) {
        this.findBeansOfTypeService = findBeansOfTypeService;
    }

    /**
     * PostProcessor Runner
     */
    @Override
    public void assemble() {
        findBeansOfTypeService.findAllByList(PostProcessor.class)
                .forEach(Runnable::run);
    }

    @Override
    public int getOrder() {
        return FluentConstants.HIGH_ORDER;
    }
}
