package org.khasanof.collector;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 12:27 PM
 */
public interface AnnotationMethodContext<P, R> extends GenericMethodContext<P, R> {

    Optional<R> findByAnnotationClass(Class<? extends Annotation> annotation);

    boolean containsByAnnotationClass(Class<? extends Annotation> annotation);

}
