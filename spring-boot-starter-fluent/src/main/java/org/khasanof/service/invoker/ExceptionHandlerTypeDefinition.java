package org.khasanof.service.invoker;

import org.khasanof.annotation.exception.HandleException;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.feature.method.MethodType;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.service.invoker
 * @since 1/27/2024 8:55 PM
 */
@Component
public class ExceptionHandlerTypeDefinition implements MethodTypeDefinition {

    @Override
    public boolean isMatch(Method method) {
        return AnnotationUtils.hasAnnotation(method, HandleException.class, false);
    }

    @Override
    public MethodType methodType() {
        return DefaultMethodType.EXCEPTION_HANDLER;
    }
}
