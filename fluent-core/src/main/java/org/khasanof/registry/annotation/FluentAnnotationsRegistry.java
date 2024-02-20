package org.khasanof.registry.annotation;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.registry.annotation
 * @since 2/19/2024 9:20 PM
 */
public interface FluentAnnotationsRegistry {

    /**
     *
     * @return
     */
    Set<Class<?>> getAnnotations();

    /**
     *
     * @param annotations
     */
    void addAnnotations(Set<Class<?>> annotations);

    /**
     *
     * @param annotation
     */
    void addAnnotation(Class<?> annotation);
}
