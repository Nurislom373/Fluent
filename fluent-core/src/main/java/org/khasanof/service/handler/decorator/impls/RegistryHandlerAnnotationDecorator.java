package org.khasanof.service.handler.decorator.impls;

import org.khasanof.feature.annotation.AnnotationHandler;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.registry.annotation.AnnotationHandlerRegistryContainer;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 9:00 PM
 */
public class RegistryHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final AnnotationHandlerRegistryContainer handlerRegistryContainer;

    public RegistryHandlerAnnotationDecorator(AnnotationHandlerRegistryContainer handlerRegistryContainer) {
        this.handlerRegistryContainer = handlerRegistryContainer;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        checkAnnotationHandler(registry);
        handlerRegistryContainer.addAnnotationHandler(registry.getAnnotationHandler());
    }

    private void checkAnnotationHandler(HandlerAnnotationRegistry registry) {
        AnnotationHandler annotationHandler = registry.getAnnotationHandler();

        if (Objects.isNull(annotationHandler)) {
            throw new RuntimeException("AnnotationHandler must not be null!");
        }

        if (!Objects.equals(annotationHandler.getAnnotation(), registry.getAnnotation())) {
            throw new RuntimeException("AnnotationHandler and Annotation must be equal!");
        }
        internalCheckAnnotationHandler(registry.getAnnotationHandler());
    }

    private void internalCheckAnnotationHandler(AnnotationHandler annotationHandler) {
        if (Objects.isNull(annotationHandler.getAnnotation())) {
            throw new RuntimeException("AnnotationHandler annotation property must not be null!");
        }

        if (Objects.equals(annotationHandler.isRepeatable(), Boolean.TRUE)) {
            if (Objects.nonNull(annotationHandler.repeatableAnnotationHandler())) {
                internalCheckAnnotationHandler(annotationHandler.repeatableAnnotationHandler());
            }
        }
    }
}
