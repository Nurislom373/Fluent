package org.khasanof.factories.method;

import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.models.condition.MethodCondition;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.factories.method
 * @since 12/26/2023 9:49 PM
 */
@Component
public class DefaultMethodCheckConditionFactoryImpl extends DefaultMethodCheckConditionFactory {

    private final Class<? extends Annotation> annotation = ProcessUpdate.class;

    @Override
    public Set<MethodCondition> create() {
        return Set.of(firstCondition());
    }

    private MethodCondition firstCondition() {
        return method -> AnnotationUtils.hasAnnotation(method, annotation, true);
    }
}
