package org.khasanof.utils;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 05.07.2023 22:33
 */
public class ReflectionUtils {

    private static final Reflections systemReflections = new Reflections("org.khasanof");

    public static Reflections getReflections() {
        return systemReflections;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getSubTypesObject(Class<T> clazz) {
        return (List<T>) systemReflections.getSubTypesOf(clazz).stream().map(clz -> {
            try {
                return clz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public static Set<Class<?>> getSubTypesSuperAnnotation(Class<? extends Annotation> annotation) {
        return systemReflections.getTypesAnnotatedWith(annotation).stream().filter(Class::isAnnotation)
                .collect(Collectors.toSet());
    }
}
