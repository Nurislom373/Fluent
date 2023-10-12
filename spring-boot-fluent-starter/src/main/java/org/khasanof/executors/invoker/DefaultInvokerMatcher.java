package org.khasanof.executors.invoker;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.enums.MatchScope;
import org.khasanof.model.InvokerMethod;
import org.khasanof.model.InvokerModel;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 8/11/2023 10:00 PM
 */
@Component(value = DefaultInvokerMatcher.NAME)
public class DefaultInvokerMatcher {

    public static final String NAME = "defaultInvokerMatcher";

    public boolean messageScopeEq(InvokerModel invokerModel) {
        Annotation[] annotations = invokerModel.getClassEntry().getKey().getDeclaredAnnotations();
        return Arrays.stream(annotations).anyMatch(annotation -> {
            if (annotation.annotationType().equals(HandleMessage.class)) {
                HandleMessage handleMessage = (HandleMessage) annotation;
                return handleMessage.scope().equals(MatchScope.VAR_EXPRESSION);
            }
            return false;
        });
    }

    public boolean messageScopeEq(InvokerMethod method) {
        Annotation[] annotations = method.getMethod().getDeclaredAnnotations();
        return Arrays.stream(annotations).anyMatch(annotation -> {
            if (annotation.annotationType().equals(HandleMessage.class)) {
                HandleMessage handleMessage = (HandleMessage) annotation;
                return handleMessage.scope().equals(MatchScope.VAR_EXPRESSION);
            }
            return false;
        });
    }

}
