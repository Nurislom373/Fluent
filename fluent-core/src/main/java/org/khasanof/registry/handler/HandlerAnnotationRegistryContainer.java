package org.khasanof.registry.handler;

import org.khasanof.feature.annotation.HandlerAnnotationRegistry;

import java.util.List;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.registry.handler
 * @since 2/19/2024 10:38 PM
 */
public interface HandlerAnnotationRegistryContainer {

    /**
     *
     * @return
     */
    Set<HandlerAnnotationRegistry> getHandlerAnnotationRegistries();

    /**
     *
     * @param annotationRegistries
     */
    void addHandlerAnnotationRegistries(List<HandlerAnnotationRegistry> annotationRegistries);

    /**
     *
     * @param annotationRegistry
     */
    void addHandlerAnnotationRegistry(HandlerAnnotationRegistry annotationRegistry);
}
