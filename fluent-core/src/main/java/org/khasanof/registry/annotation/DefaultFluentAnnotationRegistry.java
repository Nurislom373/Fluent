package org.khasanof.registry.annotation;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.registry.annotation
 * @since 2/19/2024 9:22 PM
 */
public class DefaultFluentAnnotationRegistry implements FluentAnnotationsRegistry {

    private final Set<Class<?>> annotations = new HashSet<>();

    @Override
    public Set<Class<?>> getAnnotations() {
        return this.annotations;
    }

    @Override
    public void addAnnotations(Set<Class<?>> annotations) {
        if (Objects.nonNull(annotations)) {
            annotations.forEach(this::addAnnotation);
        }
    }

    @Override
    public void addAnnotation(Class<?> annotation) {
        if (Objects.nonNull(annotation)) {
            this.annotations.add(annotation);
        }
    }
}
