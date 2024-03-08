package org.khasanof.models.meta;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.models
 * @since 2/26/2024 10:39 PM
 */
public interface AnnotatedTypeMetadata {

    /**
     *
     * @return
     */
    Annotation getAnnotation();

    /**
     *
     * @return
     * @param <T>
     */
    <T> T getAnnotationCast();

    /**
     *
     * @return
     */
    Method getAnnotatedMethod();

    /**
     *
     * @return
     */
    Class<?> getAnnotatedClass();
}
