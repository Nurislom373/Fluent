package org.khasanof.service.invoker;

import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.feature.method.MethodType;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.service.invoker
 * @since 1/27/2024 8:52 PM
 */
@Component
public class DefaultTypeDefinition implements MethodTypeDefinition {

    @Override
    public boolean isMatch(Method method) {
        return AnnotationUtils.hasAnnotation(method, ProcessUpdate.class, true);
    }

    @Override
    public MethodType methodType() {
        return DefaultMethodType.DEFAULT;
    }
}
