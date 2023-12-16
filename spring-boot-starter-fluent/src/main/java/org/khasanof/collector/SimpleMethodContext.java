package org.khasanof.collector;

import org.khasanof.enums.HandleClasses;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 12:48 PM
 */
public interface SimpleMethodContext extends AssembleMethods, AnnotationMethodContext<HandleClasses, Map<Method, Object>> {

    @Override
    default Map<Method, Object> findByAnnotationClass(Class<? extends Annotation> annotation) {
        return find(HandleClasses.getHandleWithType(annotation));
    }

    @Override
    default boolean containsByAnnotationClass(Class<? extends Annotation> annotation) {
        return contains(HandleClasses.getHandleWithType(annotation));
    }

}
