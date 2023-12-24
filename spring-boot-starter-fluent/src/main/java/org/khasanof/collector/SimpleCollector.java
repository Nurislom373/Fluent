package org.khasanof.collector;

import org.khasanof.collector.questMethod.SearchMethod;
import org.khasanof.enums.HandleClasses;
import org.khasanof.enums.HandleType;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 12:22 PM
 */
@SuppressWarnings("unchecked")
@Component(SimpleCollector.NAME)
public class SimpleCollector extends AbstractCollector implements Collector<Class<? extends Annotation>> {

    public static final String NAME = "simpleCollector";

    public SimpleCollector(SearchMethod<HandleClasses> questMethod, AnnotationMethodContext<HandleClasses, Map<Method, Object>> annotationContext) {
        super(questMethod, annotationContext);
    }

    @Override
    public Optional<SimpleInvoker> getInvokerResult(Object value, Class<? extends Annotation> annotation) {
        return questMethod.getMethodValueAnn(value, HandleClasses.getHandleWithType(annotation));
    }

    @Override
    public Optional<SimpleInvoker> getHandleAnyMethod(HandleType handleType) {
        return questMethod.getHandleAnyMethod(handleType);
    }

    @Override
    public Set<SimpleInvoker> getAllHandleAnyMethod(HandleType handleType) {
        return questMethod.getAllHandleAnyMethod(handleType);
    }

    @Override
    public boolean hasHandle(Class<? extends Annotation> annotation) {
        return annotationContext.containsByAnnotationClass(annotation);
    }

}
