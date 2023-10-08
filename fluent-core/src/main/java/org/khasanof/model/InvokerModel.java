package org.khasanof.model;

import lombok.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 13.07.2023 21:16
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InvokerModel {

    private String name;
    private Class<? extends Annotation> annotation;
    private List<Class<?>> methodParams;
    private AdditionalParam additionalParam;
    private Object[] args;
    private Map.Entry<Method, Object> classEntry;
    private AdditionalChecks additionalChecks;
    private boolean isSuperAnnotation;
    private boolean hasMainParam;
    private boolean isInputSystem;
    private boolean hasClassEntry;
    private boolean canBeNoParam;

    public InvokerModel(String name, boolean isSuperAnnotation, Class<? extends Annotation> annotation,
                        List<Class<?>> methodParams, boolean hasMainParam, AdditionalParam mainParam) {
        this.name = name;
        this.isSuperAnnotation = isSuperAnnotation;
        this.annotation = annotation;
        this.methodParams = methodParams;
        this.hasMainParam = hasMainParam;
        this.additionalParam = mainParam;
    }

    public InvokerModel(String name, boolean isSuperAnnotation, Class<? extends Annotation> annotation,
                        List<Class<?>> methodParams, boolean hasMainParam, AdditionalParam mainParam, AdditionalChecks checks) {
        this.name = name;
        this.isSuperAnnotation = isSuperAnnotation;
        this.annotation = annotation;
        this.methodParams = methodParams;
        this.hasMainParam = hasMainParam;
        this.additionalParam = mainParam;
        this.additionalChecks = checks;
    }

    public InvokerModel(String name, boolean isSuperAnnotation, Class<? extends Annotation> annotation,
                        List<Class<?>> methodParams, boolean hasMainParam, AdditionalParam mainParam, boolean canBeNoParam) {
        this.name = name;
        this.isSuperAnnotation = isSuperAnnotation;
        this.annotation = annotation;
        this.methodParams = methodParams;
        this.hasMainParam = hasMainParam;
        this.additionalParam = mainParam;
        this.canBeNoParam = canBeNoParam;
    }

    public InvokerModel(String name, boolean isSuperAnnotation, Class<? extends Annotation> annotation,
                        List<Class<?>> methodParams, boolean hasMainParam, boolean isInputSystem) {
        this.name = name;
        this.isSuperAnnotation = isSuperAnnotation;
        this.annotation = annotation;
        this.methodParams = methodParams;
        this.hasMainParam = hasMainParam;
        this.isInputSystem = isInputSystem;
    }

    public InvokerModel methodSClass(Map.Entry<Method, Object> entry) {
        this.classEntry = entry;
        this.hasClassEntry = Objects.nonNull(entry);
        return this;
    }

}
