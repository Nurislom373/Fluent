package org.khasanof.feature.interceptor.invoke;

import org.jetbrains.annotations.NotNull;
import org.khasanof.annotation.process.ProcessCondition;
import org.khasanof.feature.interceptor.InvokerMethodInterceptor;
import org.khasanof.models.invoker.InvokerParam;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Nurislom
 * @see org.khasanof.feature.interceptor.invoke
 * @since 2/25/2024 8:23 PM
 */
@Component
public class InvokerMethodConditionsInterceptor implements InvokerMethodInterceptor {

    @Override
    public void preHandle(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params) {
        Annotation[] methodAnnotations = getMethodDeclaredAnnotations(entry);
        Annotation[] classAnnotations = getClassDeclaredAnnotations(entry);

        if (methodAnnotations.length == 0 && classAnnotations.length == 0) {
            return;
        }
        collectConditionAnnotations(classAnnotations, getClassConsumer(params));
        collectConditionAnnotations(methodAnnotations, getMethodConsumer(entry, params));
    }

    @NotNull
    private Annotation[] getMethodDeclaredAnnotations(Map.Entry<Method, Object> entry) {
        return entry.getKey().getDeclaredAnnotations();
    }

    @NotNull
    private Annotation[] getClassDeclaredAnnotations(Map.Entry<Method, Object> entry) {
        return entry.getValue().getClass().getDeclaredAnnotations();
    }

    @NotNull
    private Consumer<Annotation> getClassConsumer(Map<InvokerParam, Object> params) {
        return annotation -> params.put(InvokerParam.CONDITIONS, annotation);
    }

    @NotNull
    private Consumer<Annotation> getMethodConsumer(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params) {
        return annotation -> {
            params.put(InvokerParam.CONDITIONS, annotation);
            params.put(InvokerParam.CONDITIONS_METHOD, entry.getKey());
        };
    }

    private void collectConditionAnnotations(Annotation[] annotations, Consumer<Annotation> consumer) {
        Arrays.stream(annotations)
                .filter(this::isAnnotationPresent)
                .findFirst()
                .ifPresent(consumer);
    }

    private boolean isAnnotationPresent(Annotation annotation) {
        return annotation.annotationType()
                .isAnnotationPresent(ProcessCondition.class);
    }
}
