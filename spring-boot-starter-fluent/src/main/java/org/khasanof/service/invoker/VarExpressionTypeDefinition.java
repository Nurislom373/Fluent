package org.khasanof.service.invoker;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.constants.FluentConstants;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.enums.MatchScope;
import org.khasanof.feature.method.MethodType;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.service.invoker
 * @since 1/27/2024 8:41 PM
 */
@Component
public class VarExpressionTypeDefinition implements MethodTypeDefinition {

    @Override
    public boolean isMatch(Method method) {
        return AnnotationUtils.hasAnnotation(method, HandleMessage.class, false) && checkHandleMessageScope(method);
    }

    private boolean checkHandleMessageScope(Method method) {
        return Arrays.stream(method.getDeclaredAnnotations())
                .anyMatch(this::annotationMatcher);
    }

    private boolean annotationMatcher(Annotation annotation) {
        return annotation.annotationType().equals(HandleMessage.class) && ((HandleMessage) annotation).scope().equals(MatchScope.VAR_EXPRESSION);
    }

    @Override
    public MethodType methodType() {
        return DefaultMethodType.VAR_EXPRESSION;
    }

    @Override
    public int getOrder() {
        return FluentConstants.HIGH_ORDER;
    }
}
