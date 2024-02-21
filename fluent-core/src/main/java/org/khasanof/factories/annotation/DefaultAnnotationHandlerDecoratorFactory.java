package org.khasanof.factories.annotation;

import org.khasanof.collector.method.checker.HandleMethodCheckerMediator;
import org.khasanof.mediator.MethodTypeDefinitionMediator;
import org.khasanof.registry.annotation.AnnotationHandlerRegistryContainer;
import org.khasanof.registry.annotation.FluentAnnotationsRegistry;
import org.khasanof.registry.appropriate.AppropriateMethodRegistryContainer;
import org.khasanof.registry.appropriate.AppropriateTypeRegistryContainer;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.handler.HandlerAnnotationDefinition;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;
import org.khasanof.service.handler.decorator.HandlerAnnotationDecorator;
import org.khasanof.service.handler.decorator.impls.*;

/**
 * @author Nurislom
 * @see org.khasanof.factories.annotation
 * @since 2/20/2024 11:04 PM
 */
public class DefaultAnnotationHandlerDecoratorFactory implements AnnotationHandlerDecoratorFactory {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final FluentAnnotationsRegistry fluentAnnotationsRegistry;
    private final HandleMethodCheckerMediator handleMethodCheckerMediator;
    private final MethodTypeDefinitionMediator methodTypeDefinitionMediator;
    private final AppropriateTypeRegistryContainer appropriateTypeRegistryContainer;
    private final AppropriateMethodRegistryContainer appropriateMethodRegistryContainer;
    private final AnnotationHandlerRegistryContainer annotationHandlerRegistryContainer;

    public DefaultAnnotationHandlerDecoratorFactory(FindBeansOfTypeService findBeansOfTypeService,
                                                    FluentAnnotationsRegistry fluentAnnotationsRegistry,
                                                    HandleMethodCheckerMediator handleMethodCheckerMediator,
                                                    MethodTypeDefinitionMediator methodTypeDefinitionMediator,
                                                    AppropriateTypeRegistryContainer appropriateTypeRegistryContainer,
                                                    AppropriateMethodRegistryContainer appropriateMethodRegistryContainer,
                                                    AnnotationHandlerRegistryContainer annotationHandlerRegistryContainer) {

        this.findBeansOfTypeService = findBeansOfTypeService;
        this.fluentAnnotationsRegistry = fluentAnnotationsRegistry;
        this.handleMethodCheckerMediator = handleMethodCheckerMediator;
        this.methodTypeDefinitionMediator = methodTypeDefinitionMediator;
        this.appropriateTypeRegistryContainer = appropriateTypeRegistryContainer;
        this.appropriateMethodRegistryContainer = appropriateMethodRegistryContainer;
        this.annotationHandlerRegistryContainer = annotationHandlerRegistryContainer;
    }

    @Override
    public HandlerAnnotationDecorator create(HandlerAnnotationDefinition handlerAnnotationDefinition) {
        BaseHandlerAnnotationDecorator handlerAnnotationDecorator = new DefaultHandlerAnnotationDecorator(fluentAnnotationsRegistry);

        RegistryHandlerAnnotationDecorator registryHandlerAnnotationDecorator = createRegistry();
        registryHandlerAnnotationDecorator.decorator(handlerAnnotationDecorator);
        handlerAnnotationDecorator = registryHandlerAnnotationDecorator;

        if (!handlerAnnotationDefinition.isDefault()) {
            MethodTypeAnnotationHandlerDecorator methodTypeAnnotationHandlerDecorator = createMethodType();
            methodTypeAnnotationHandlerDecorator.decorator(handlerAnnotationDecorator);
            handlerAnnotationDecorator = methodTypeAnnotationHandlerDecorator;

            PerformHandlerAnnotationDecorator perform = createPerform();
            perform.decorator(handlerAnnotationDecorator);
            handlerAnnotationDecorator = perform;

            CheckerHandlerAnnotationDecorator checker = createChecker();
            checker.decorator(handlerAnnotationDecorator);
            handlerAnnotationDecorator = checker;
        }

        MatcherHandlerAnnotationDecorator matcher = createMatcher();
        matcher.decorator(handlerAnnotationDecorator);
        handlerAnnotationDecorator = matcher;

        if (handlerAnnotationDefinition.isNewAppropriateUpdateType()) {
            UpdateTypeHandlerAnnotationDecorator updateType = createUpdateType();
            updateType.decorator(handlerAnnotationDecorator);
            handlerAnnotationDecorator = updateType;
        }

        if (handlerAnnotationDefinition.isNewAppropriateUpdateMethod()) {
            UpdateMethodHandlerAnnotationDecorator updateMethod = createUpdateMethod();
            updateMethod.decorator(handlerAnnotationDecorator);
            handlerAnnotationDecorator = updateMethod;
        }

        return handlerAnnotationDecorator;
    }

    private RegistryHandlerAnnotationDecorator createRegistry() {
        return new RegistryHandlerAnnotationDecorator(annotationHandlerRegistryContainer);
    }

    private PerformHandlerAnnotationDecorator createPerform() {
        return new PerformHandlerAnnotationDecorator(findBeansOfTypeService);
    }

    private CheckerHandlerAnnotationDecorator createChecker() {
        return new CheckerHandlerAnnotationDecorator(findBeansOfTypeService, handleMethodCheckerMediator);
    }

    private MethodTypeAnnotationHandlerDecorator createMethodType() {
        return new MethodTypeAnnotationHandlerDecorator(methodTypeDefinitionMediator);
    }

    private MatcherHandlerAnnotationDecorator createMatcher() {
        return new MatcherHandlerAnnotationDecorator(findBeansOfTypeService);
    }

    private UpdateTypeHandlerAnnotationDecorator createUpdateType() {
        return new UpdateTypeHandlerAnnotationDecorator(findBeansOfTypeService, appropriateTypeRegistryContainer);
    }

    private UpdateMethodHandlerAnnotationDecorator createUpdateMethod() {
        return new UpdateMethodHandlerAnnotationDecorator(findBeansOfTypeService, appropriateMethodRegistryContainer);
    }
}
