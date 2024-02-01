package org.khasanof.service.invoker;

import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.enums.MethodType;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.service.invoker
 * @since 1/27/2024 8:53 PM
 */
@Component
public class ProcessFileTypeDefinition implements MethodTypeDefinition {

    @Override
    public boolean isMatch(Method method) {
        return AnnotationUtils.hasAnnotation(method, ProcessFile.class, true) && (method.getParameterCount() > 2);
    }

    @Override
    public MethodType methodType() {
        return MethodType.PROCESS_FILE;
    }
}
