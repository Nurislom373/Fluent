package org.khasanof.collector.method;

import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.utils.AnnotationUtils;
import org.khasanof.utils.ReflectionUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Nurislom
 * @see org.khasanof.collector.methodChecker
 * @since 19.07.2023 21:40
 */
@Component
public class SimpleMethodChecker extends AbstractMethodChecker {

    private final Set<Class<?>> classes = ReflectionUtils.getSubTypesSuperAnnotation(ProcessUpdate.class);

    @Override
    public boolean valid(Method method) {
        boolean annotationValid;
        int length = method.getAnnotations().length;

        if (length == 0) {
            return false;
        } else {
            AtomicInteger matchCount = new AtomicInteger();
            Arrays.stream(method.getAnnotations())
                    .forEach(annotation -> {
                        if (classes.contains(annotation.annotationType())) {
                            matchCount.getAndIncrement();
                        }
                    });
            annotationValid = matchCount.get() == 1;
        }

        int parameterCount = method.getParameterCount();

        boolean profileFile = AnnotationUtils.hasAnnotation(method, ProcessFile.class, true);

        if (profileFile) {
            return hasProcessFile(method, annotationValid);
        }

        return match(method, annotationValid, parameterCount);
    }

    private boolean match(Method method, boolean annotationValid, int parameterCount) {
        boolean parameterValid;
        if (parameterCount != 2) {
            if (!annotationValid) {
                return false;
            }
        } else {
            parameterValid = paramsTypeCheckV2(method.getParameterTypes(), MAIN_PARAMS);
            if (!parameterValid) {
                throw new RuntimeException("There is an error in the method parameters with handle annotations!");
            }
        }
        return annotationValid;
    }

    private boolean hasProcessFile(Method method, boolean annotationValid) {
        boolean parameterValid;
        int parameterCount = method.getParameterCount();

        if (parameterCount < 2 || parameterCount > 3) {
            throw new InvalidParamsException("There is an error in the method parameters with handle annotations!");
        } else {
            if (parameterCount == 3) {
                Class<?>[] mainParams = new Class[MAIN_PARAMS.length + 1];
                System.arraycopy(MAIN_PARAMS, 0, mainParams, 0, MAIN_PARAMS.length);
                mainParams[mainParams.length - 1] = InputStream.class;

                parameterValid = paramsTypeCheckV3(method.getParameterTypes(), mainParams);
            } else {
                parameterValid = paramsTypeCheckV2(method.getParameterTypes(), MAIN_PARAMS);
            }

            if (!parameterValid) {
                throw new RuntimeException("There is an error in the method parameters with handle annotations!");
            }
        }

        return annotationValid;
    }

    @Override
    public Class<? extends Annotation> getType() {
        return ProcessUpdate.class;
    }

    @Override
    public boolean hasSuperAnnotation() {
        return true;
    }

    @Override
    public boolean hasAny() {
        return false;
    }
}
