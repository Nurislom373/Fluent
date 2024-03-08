package org.khasanof.models.meta;

import lombok.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.models.meta
 * @since 2/28/2024 12:26 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DefaultAnnotatedTypeMetadata implements AnnotatedTypeMetadata {

    private Annotation annotation;
    private Method annotatedMethod;
    private Class<?> annotatedClass;

    @Override
    public <T> T getAnnotationCast() {
        try {
            return (T) annotation;
        } catch (RuntimeException e) {
            return null;
        }
    }
}
