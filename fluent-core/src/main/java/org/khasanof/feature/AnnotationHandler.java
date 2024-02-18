package org.khasanof.feature;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.feature
 * @since 2/17/2024 7:01 PM
 */
public interface AnnotationHandler {

    /**
     *
     * @return
     */
    Class<? extends Annotation> getAnnotation();

    /**
     *
     * @return
     */
    default boolean isRepeatable() {
        return false;
    }

    /**
     *
     * @return
     */
    default AnnotationHandler repeatableAnnotationHandler() {
        return null;
    }

    /**
     * Null safe equals method for AnnotationHandler
     *
     * @param annotation {@link Annotation}
     * @return equals result
     */
    default boolean equalsAnnotation(Class<? extends Annotation> annotation) {
        return Objects.equals(getAnnotation(), annotation);
    }
}
