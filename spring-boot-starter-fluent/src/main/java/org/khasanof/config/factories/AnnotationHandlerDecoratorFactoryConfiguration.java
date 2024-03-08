package org.khasanof.config.factories;

import org.khasanof.collector.method.checker.HandleMethodCheckerMediator;
import org.khasanof.factories.annotation.AnnotationHandlerDecoratorFactory;
import org.khasanof.factories.annotation.DefaultAnnotationHandlerDecoratorFactory;
import org.khasanof.mediator.MethodTypeDefinitionMediator;
import org.khasanof.registry.annotation.AnnotationHandlerRegistryContainer;
import org.khasanof.registry.annotation.FluentAnnotationsRegistry;
import org.khasanof.registry.appropriate.AppropriateMethodRegistryContainer;
import org.khasanof.registry.appropriate.AppropriateTypeRegistryContainer;
import org.khasanof.registry.binder.UpdateTypeBinderRegistry;
import org.khasanof.service.FindBeansOfTypeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.factories
 * @since 2/20/2024 11:34 PM
 */
@Configuration
public class AnnotationHandlerDecoratorFactoryConfiguration {

    /**
     *
     * @param findBeansOfTypeService
     * @param updateTypeBinderRegistry
     * @param fluentAnnotationsRegistry
     * @param handleMethodCheckerMediator
     * @param methodTypeDefinitionMediator
     * @param appropriateTypeRegistryContainer
     * @param appropriateMethodRegistryContainer
     * @param annotationHandlerRegistryContainer
     * @return
     */
    @Bean
    public AnnotationHandlerDecoratorFactory annotationHandlerDecoratorFactory(FindBeansOfTypeService findBeansOfTypeService,
                                                                               UpdateTypeBinderRegistry updateTypeBinderRegistry,
                                                                               FluentAnnotationsRegistry fluentAnnotationsRegistry,
                                                                               HandleMethodCheckerMediator handleMethodCheckerMediator,
                                                                               MethodTypeDefinitionMediator methodTypeDefinitionMediator,
                                                                               AppropriateTypeRegistryContainer appropriateTypeRegistryContainer,
                                                                               AppropriateMethodRegistryContainer appropriateMethodRegistryContainer,
                                                                               AnnotationHandlerRegistryContainer annotationHandlerRegistryContainer) {

        return new DefaultAnnotationHandlerDecoratorFactory(findBeansOfTypeService, updateTypeBinderRegistry,
                fluentAnnotationsRegistry, handleMethodCheckerMediator, methodTypeDefinitionMediator, appropriateTypeRegistryContainer,
                appropriateMethodRegistryContainer, annotationHandlerRegistryContainer);
    }
}
