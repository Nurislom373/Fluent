package org.khasanof.service.handler;

import org.khasanof.factories.annotation.AnnotationHandlerDecoratorFactory;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.registry.handler.HandlerAnnotationRegistryContainer;
import org.khasanof.service.handler.decorator.HandlerAnnotationDecorator;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler
 * @since 2/19/2024 10:44 PM
 */
public class DefaultHandlerAnnotationRegistryService implements HandlerAnnotationRegistryService {

    private final HandlerAnnotationRegistryContainer registryContainer;
    private final AnnotationHandlerDecoratorFactory annotationHandlerDecoratorFactory;
    private final HandlerAnnotationDefinitionService handlerAnnotationDefinitionService;

    public DefaultHandlerAnnotationRegistryService(HandlerAnnotationRegistryContainer registryContainer,
                                                   AnnotationHandlerDecoratorFactory annotationHandlerDecoratorFactory,
                                                   HandlerAnnotationDefinitionService handlerAnnotationDefinitionService) {

        this.registryContainer = registryContainer;
        this.annotationHandlerDecoratorFactory = annotationHandlerDecoratorFactory;
        this.handlerAnnotationDefinitionService = handlerAnnotationDefinitionService;
    }

    @Override
    public void registry() {
        Set<HandlerAnnotationRegistry> annotationRegistries = registryContainer.getHandlerAnnotationRegistries();
        annotationRegistries.forEach(this::registry);
    }

    private void registry(HandlerAnnotationRegistry annotationRegistry) {
        HandlerAnnotationDefinition definition = getDefinition(annotationRegistry);
        HandlerAnnotationDecorator decorator = annotationHandlerDecoratorFactory.create(definition);
        decorator.execute(annotationRegistry);
    }

    private HandlerAnnotationDefinition getDefinition(HandlerAnnotationRegistry registry) {
        return handlerAnnotationDefinitionService.definition(registry);
    }
}
