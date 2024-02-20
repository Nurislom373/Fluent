package org.khasanof.registry.handler;

import org.khasanof.feature.annotation.HandlerAnnotationRegistry;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.registry.handler
 * @since 2/19/2024 10:40 PM
 */
public class DefaultHandlerAnnotationRegistryContainer implements HandlerAnnotationRegistryContainer {

    private final Set<HandlerAnnotationRegistry> annotationRegistries = new HashSet<>();

    @Override
    public Set<HandlerAnnotationRegistry> getHandlerAnnotationRegistries() {
        return this.annotationRegistries;
    }

    @Override
    public void addHandlerAnnotationRegistries(List<HandlerAnnotationRegistry> annotationRegistries) {
        if (Objects.nonNull(annotationRegistries)) {
            annotationRegistries.forEach(this::addHandlerAnnotationRegistry);
        }
    }

    @Override
    public void addHandlerAnnotationRegistry(HandlerAnnotationRegistry annotationRegistry) {
        if (Objects.nonNull(annotationRegistry)) {
            this.annotationRegistries.add(annotationRegistry);
        }
    }
}
