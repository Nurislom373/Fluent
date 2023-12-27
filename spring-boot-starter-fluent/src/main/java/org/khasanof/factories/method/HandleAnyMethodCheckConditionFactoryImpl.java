package org.khasanof.factories.method;

import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.models.condition.MethodCondition;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.factories.method
 * @since 12/27/2023 10:10 PM
 */
@Component
public class HandleAnyMethodCheckConditionFactoryImpl extends HandleAnyMethodCheckConditionFactory {

    private final Class<? extends Annotation> annotation = HandleAny.class;

    @Override
    public Set<MethodCondition> create() {
        return Set.of(firstCondition());
    }

    private MethodCondition firstCondition() {
        return method -> AnnotationUtils.hasAnnotation(method, annotation, false);
    }
}
