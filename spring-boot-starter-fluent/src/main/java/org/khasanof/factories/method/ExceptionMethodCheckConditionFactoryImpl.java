package org.khasanof.factories.method;

import org.khasanof.annotation.exception.HandleException;
import org.khasanof.models.condition.MethodGenericCondition;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.factories.method
 * @since 12/27/2023 10:14 PM
 */
@Component
public class ExceptionMethodCheckConditionFactoryImpl extends ExceptionMethodCheckConditionFactory {

    private final Class<? extends Annotation> annotation = HandleException.class;

    @Override
    public Set<MethodGenericCondition> create() {
        return Set.of(firstCondition());
    }

    private MethodGenericCondition firstCondition() {
        return method -> AnnotationUtils.hasAnnotation(method, annotation, false);
    }
}
