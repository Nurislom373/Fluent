package org.khasanof.registry.annotation;

import org.khasanof.feature.AnnotationHandler;

import java.util.List;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.registry.annotation
 * @since 2/17/2024 8:12 PM
 */
public interface AnnotationHandlerRegistryContainer {

    /**
     *
     * @return
     */
    Set<AnnotationHandler> getAnnotationHandlers();

    /**
     *
     * @param handlers
     */
    void addAnnotationHandlers(Set<AnnotationHandler> handlers);

    /**
     *
     * @param handler
     */
    void addAnnotationHandler(AnnotationHandler handler);
}
