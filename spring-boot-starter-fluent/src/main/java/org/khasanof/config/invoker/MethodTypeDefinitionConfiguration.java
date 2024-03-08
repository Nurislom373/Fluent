package org.khasanof.config.invoker;

import org.khasanof.mediator.DefaultMethodTypeDefinitionMediator;
import org.khasanof.mediator.MethodTypeDefinitionMediator;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.invoker.MethodTypeDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.invoker
 * @since 1/27/2024 9:09 PM
 */
@Configuration
public class MethodTypeDefinitionConfiguration {

    /**
     *
     * @param findBeansOfTypeService
     * @return
     */
    @Bean
    @ConditionalOnBean({FindBeansOfTypeService.class})
    public MethodTypeDefinitionMediator methodTypeDefinition(FindBeansOfTypeService findBeansOfTypeService) {
        DefaultMethodTypeDefinitionMediator methodTypeDefinitionMediator = new DefaultMethodTypeDefinitionMediator();
        methodTypeDefinitionMediator.addMethodTypeDefinitions(findBeansOfTypeService.findAllByList(MethodTypeDefinition.class));
        return methodTypeDefinitionMediator;
    }
}
