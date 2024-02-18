package org.khasanof.service.invoker;

import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.service.invoker
 * @since 1/27/2024 8:56 PM
 */
@Component
public class HandleAnyTypeDefinition implements MethodTypeDefinition {

    @Override
    public boolean isMatch(Method method) {
        return AnnotationUtils.hasAnnotation(method, HandleAny.class, false);
    }

    @Override
    public DefaultMethodType methodType() {
        return DefaultMethodType.HANDLE_ANY;
    }
}
