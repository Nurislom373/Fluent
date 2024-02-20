package org.khasanof.registry.annotation;

import org.khasanof.feature.annotation.AnnotationHandler;

import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.registry.annotation
 * @since 2/17/2024 8:13 PM
 */
public class DefaultAnnotationHandlerRegistryContainer implements AnnotationHandlerRegistryContainer {

    private final Set<AnnotationHandler> handlers = new HashSet<>();

    @Override
    public Set<AnnotationHandler> getAnnotationHandlers() {
        return this.handlers;
    }

    @Override
    public void addAnnotationHandlers(Set<AnnotationHandler> handlers) {
        if (Objects.nonNull(handlers)) {
            handlers.forEach(this::addAnnotationHandler);
        }
    }

    @Override
    public void addAnnotationHandler(AnnotationHandler handler) {
        if (Objects.nonNull(handler)) {
            this.handlers.add(handler);
        }
    }
}
