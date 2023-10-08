package org.khasanof.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * this class provides different methods for working with annotations.
 *
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 12.07.2023 23:41
 */
public abstract class AnnotationUtils {

    /**
     * The hasAnnotation method is used to determine whether a given method has an annotation.
     * The isSuper parameter, if true, checks whether one of the method annotations is annotated.
     *
     * @param method class declared method
     * @param annotation for inspection
     * @param isSuper indicates whether the method should check for annotations
     * @return true if the given method has an annotation, false otherwise.
     */
    public static boolean hasAnnotation(Method method, Class<? extends Annotation> annotation, boolean isSuper) {
        BaseUtils.checkArgsIsNonNull(method, annotation, isSuper);
        if (isSuper) {
            return Arrays.stream(method.getAnnotations())
                    .anyMatch(any -> any.annotationType()
                            .isAnnotationPresent(annotation));
        }
        return method.isAnnotationPresent(annotation);
    }

    public static Annotation getAnnotation(Method method, Class<? extends Annotation> annotation, boolean isSuper) {
        BaseUtils.checkArgsIsNonNull(method, annotation, isSuper);
        if (isSuper) {
            return Arrays.stream(method.getAnnotations())
                    .filter(f -> f.annotationType().isAnnotationPresent(annotation))
                    .findFirst().orElse(null);
        }
        return method.getAnnotation(annotation);
    }

}
