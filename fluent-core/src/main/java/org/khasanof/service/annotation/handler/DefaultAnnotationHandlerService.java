package org.khasanof.service.annotation.handler;

import org.khasanof.feature.AnnotationHandler;
import org.khasanof.registry.annotation.AnnotationHandlerRegistryContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.service.annotation.handler
 * @since 2/17/2024 8:12 PM
 */
public class DefaultAnnotationHandlerService implements AnnotationHandlerService {

    private final AnnotationHandlerRegistryContainer registryContainer;

    public DefaultAnnotationHandlerService(AnnotationHandlerRegistryContainer registryContainer) {
        this.registryContainer = registryContainer;
    }

    @Override
    public Optional<AnnotationHandler> findByMethod(Method method) {
        if (method.getDeclaredAnnotations().length == 0) {
            return Optional.empty();
        }
        return findByMethodInternal(method);
    }

    @Override
    public Optional<AnnotationHandler> findByAnnotationClass(Class<? extends Annotation> annotation) {
        return registryContainer.getAnnotationHandlers()
                .stream()
                .filter(handler -> handler.equalsAnnotation(annotation))
                .findFirst();
    }

    private Optional<AnnotationHandler> findByMethodInternal(Method method) {
        return Arrays.stream(method.getDeclaredAnnotations())
                .flatMap(annotation -> registryContainer.getAnnotationHandlers()
                        .stream()
                        .filter(handler -> handler.equalsAnnotation(annotation.annotationType())))
                .findFirst();
    }
}
