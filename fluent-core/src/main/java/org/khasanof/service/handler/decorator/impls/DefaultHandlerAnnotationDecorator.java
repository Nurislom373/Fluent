package org.khasanof.service.handler.decorator.impls;

import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.registry.annotation.FluentAnnotationsRegistry;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator
 * @since 2/20/2024 8:54 PM
 */
public class DefaultHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final FluentAnnotationsRegistry fluentAnnotationsRegistry;

    public DefaultHandlerAnnotationDecorator(FluentAnnotationsRegistry fluentAnnotationsRegistry) {
        this.fluentAnnotationsRegistry = fluentAnnotationsRegistry;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        checkAnnotation(registry.getAnnotation());
        fluentAnnotationsRegistry.addAnnotation(registry.getAnnotation());
    }

    private void checkAnnotation(Class<?> annotation) {
        if (Objects.isNull(annotation)) {
            throw new RuntimeException("Annotation must not be null!");
        }
    }
}
