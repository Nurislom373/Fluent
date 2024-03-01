package org.khasanof.feature.interceptor.invoker;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.custom.attributes.Attributes;
import org.khasanof.custom.attributes.DefaultAttributes;
import org.khasanof.factories.condition.FluentConditionFactory;
import org.khasanof.feature.condition.FluentCondition;
import org.khasanof.feature.interceptor.PostFindInvokerInterceptor;
import org.khasanof.models.meta.AnnotatedTypeMetadata;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.models.meta.DefaultAnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.feature.interceptor.invoker
 * @since 2/27/2024 11:49 PM
 */
@Component
public class PostFindInvokerConditionsInterceptor implements PostFindInvokerInterceptor {

    private final FluentConditionFactory fluentConditionFactory;

    public PostFindInvokerConditionsInterceptor(FluentConditionFactory fluentConditionFactory) {
        this.fluentConditionFactory = fluentConditionFactory;
    }

    @Override
    public boolean postHandle(SimpleInvoker simpleInvoker) {
        Map<InvokerParam, Object> params = simpleInvoker.getParams();
        if (!params.containsKey(InvokerParam.CONDITIONS)) {
            return false;
        }
        return internalProcess(simpleInvoker);
    }

    private boolean internalProcess(SimpleInvoker simpleInvoker) {
        Annotation annotation = getConditionAnnotation(simpleInvoker);
        FluentCondition fluentCondition = fluentConditionFactory.create(annotation);
        return fluentCondition.matches(createDefaultAttributes(), createMetadata(simpleInvoker.getParams(), annotation));
    }

    private AnnotatedTypeMetadata createMetadata(Map<InvokerParam, Object> params, Annotation annotation) {
        DefaultAnnotatedTypeMetadata metadata = new DefaultAnnotatedTypeMetadata();
        metadata.setAnnotation(annotation);
        metadata.setAnnotatedClass(annotation.annotationType().getDeclaringClass());

        if (params.containsKey(InvokerParam.CONDITIONS_METHOD)) {
            metadata.setAnnotatedMethod((Method) params.get(InvokerParam.CONDITIONS_METHOD));
        }
        return metadata;
    }

    private Attributes createDefaultAttributes() {
        Attributes attributes = new DefaultAttributes();
        attributes.setAttribute("update", FluentContextHolder.getCurrentFluentUpdate().getUpdate());
        return attributes;
    }

    private Annotation getConditionAnnotation(SimpleInvoker simpleInvoker) {
        return (Annotation) simpleInvoker.getParams().get(InvokerParam.CONDITIONS);
    }
}
