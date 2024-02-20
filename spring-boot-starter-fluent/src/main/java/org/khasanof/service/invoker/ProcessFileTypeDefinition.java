package org.khasanof.service.invoker;

import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.constants.FluentConstants;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.feature.method.MethodType;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.service.invoker
 * @since 1/27/2024 8:53 PM
 */
@Component
public class ProcessFileTypeDefinition implements MethodTypeDefinition {

    @Override
    public boolean isMatch(Method method) {
        return AnnotationUtils.hasAnnotation(method, ProcessFile.class, true) &&
                hasInputStreamParam(method) && (method.getParameterCount() >= 1);
    }

    private boolean hasInputStreamParam(Method method) {
        return Arrays.asList(method.getParameterTypes())
                .contains(InputStream.class);
    }

    @Override
    public MethodType methodType() {
        return DefaultMethodType.PROCESS_FILE;
    }

    @Override
    public int getOrder() {
        return FluentConstants.HIGH_ORDER;
    }
}
