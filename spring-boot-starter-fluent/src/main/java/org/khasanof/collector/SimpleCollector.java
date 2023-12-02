package org.khasanof.collector;

import org.khasanof.collector.questMethod.QuestMethod;
import org.khasanof.enums.HandleClasses;
import org.khasanof.enums.HandleType;
import org.khasanof.model.InvokerResult;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 12:22 PM
 */
@Component(SimpleCollector.NAME)
public class SimpleCollector extends AbstractCollector implements Collector<Class<? extends Annotation>> {

    public static final String NAME = "simpleCollector";

    public SimpleCollector(QuestMethod<HandleClasses> questMethod, AnnotationMethodContext<Map<Method, Object>> annotationContext) {
        super(questMethod, annotationContext);
    }

    @Override
    @SuppressWarnings("unchecked")
    public InvokerResult getInvokerResult(Object value, Class<? extends Annotation> annotation) {
        return questMethod.getMethodValueAnn(value, HandleClasses.getHandleWithType(annotation));
    }

    @Override
    public InvokerResult getHandleAnyMethod(HandleType handleType) {
        return questMethod.getHandleAnyMethod(handleType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<InvokerResult> getAllHandleAnyMethod(HandleType handleType) {
        return questMethod.getAllHandleAnyMethod(handleType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean hasHandle(Class<? extends Annotation> annotation) {
        return annotationContext.containsKey(annotation);
    }

}
