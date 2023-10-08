package org.khasanof.collector;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.springbootstarterfluent.core.collector
 * @since 8/19/2023 12:27 PM
 */
public interface AnnotationMethodContext<R> {

    R getMethodsWithAnnotation(Class<? extends Annotation> annotation);

    boolean containsKey(Class<? extends Annotation> annotation);

}
