package org.khasanof.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * this class provides different methods for working with annotations.
 *
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 12.07.2023 23:41
 */
@SuppressWarnings({"unchecked"})
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

    public static <T> T getAnnotationValue(Annotation annotation, String methodName, Class<T> returnType)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> clazz = annotation.annotationType();

        // Get all declared methods
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            // Check if the method name matches and the return type is as expected
            if (method.getName().equals(methodName) && method.getReturnType() == returnType) {
                return (T) method.invoke(annotation);
            }
        }

        // If no matching method or annotation found, return null or handle accordingly
        return null;
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
