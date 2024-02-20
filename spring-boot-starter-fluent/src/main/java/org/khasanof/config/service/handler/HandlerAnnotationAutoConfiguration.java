package org.khasanof.config.service.handler;

import org.khasanof.factories.annotation.AnnotationHandlerDecoratorFactory;
import org.khasanof.registry.appropriate.AppropriateMethodRegistryContainer;
import org.khasanof.registry.appropriate.AppropriateTypeRegistryContainer;
import org.khasanof.registry.handler.DefaultHandlerAnnotationRegistryContainer;
import org.khasanof.registry.handler.HandlerAnnotationRegistryContainer;
import org.khasanof.service.handler.DefaultHandlerAnnotationDefinitionService;
import org.khasanof.service.handler.DefaultHandlerAnnotationRegistryService;
import org.khasanof.service.handler.HandlerAnnotationDefinitionService;
import org.khasanof.service.handler.HandlerAnnotationRegistryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.service.handler
 * @since 2/20/2024 11:36 PM
 */
@Configuration
public class HandlerAnnotationAutoConfiguration {

    /**
     *
     * @return {@link HandlerAnnotationRegistryContainer} bean
     */
    @Bean
    public HandlerAnnotationRegistryContainer handlerAnnotationRegistryContainer() {
        return new DefaultHandlerAnnotationRegistryContainer();
    }

    /**
     *
     * @param typeRegistryContainer
     * @param methodRegistryContainer
     * @return {@link HandlerAnnotationDefinitionService} bean
     */
    @Bean
    public HandlerAnnotationDefinitionService handlerAnnotationDefinitionService(AppropriateTypeRegistryContainer typeRegistryContainer,
                                                                                 AppropriateMethodRegistryContainer methodRegistryContainer) {

        return new DefaultHandlerAnnotationDefinitionService(typeRegistryContainer, methodRegistryContainer);
    }

    /**
     *
     * @param registryContainer
     * @param annotationHandlerDecoratorFactory
     * @param handlerAnnotationDefinitionService
     * @return {@link HandlerAnnotationRegistryService} bean
     */
    @Bean
    public HandlerAnnotationRegistryService handlerAnnotationRegistryService(HandlerAnnotationRegistryContainer registryContainer,
                                                                             AnnotationHandlerDecoratorFactory annotationHandlerDecoratorFactory,
                                                                             HandlerAnnotationDefinitionService handlerAnnotationDefinitionService) {

        return new DefaultHandlerAnnotationRegistryService(registryContainer, annotationHandlerDecoratorFactory, handlerAnnotationDefinitionService);
    }
}
