package org.khasanof.service.annotation.handler;

import org.khasanof.feature.annotation.AnnotationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.service.annotation.handler
 * @since 2/17/2024 8:12 PM
 */
public interface AnnotationHandlerService {

    /**
     *
     * @param method
     * @return
     */
    Optional<AnnotationHandler> findByMethod(Method method);

    /**
     *
     * @param annotation
     * @return
     */
    Optional<AnnotationHandler> findByAnnotationClass(Class<? extends Annotation> annotation);
}
