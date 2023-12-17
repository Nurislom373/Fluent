package org.khasanof.executors.invoker;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.enums.MatchScope;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.models.invoker.SimpleInvokerMethod;
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

    public boolean checkHandleMessageScope(SimpleInvoker method, MatchScope scope) {
        return Arrays.stream(method.getMethod().getDeclaredAnnotations())
                .anyMatch(annotation -> annotationMatcher(scope, annotation));
    }

    private static boolean annotationMatcher(MatchScope scope, Annotation annotation) {
        return annotation.annotationType().equals(HandleMessage.class) && ((HandleMessage) annotation).scope().equals(scope);
    }

}
