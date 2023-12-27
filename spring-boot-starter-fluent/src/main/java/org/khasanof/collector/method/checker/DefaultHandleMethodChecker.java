package org.khasanof.collector.method.checker;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.ProcessType;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.factories.method.DefaultMethodCheckConditionFactory;
import org.khasanof.models.condition.MethodCondition;
import org.khasanof.utils.AnnotationUtils;
import org.khasanof.utils.ReflectionUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/26/2023 9:44 PM
 */
@Component
public class DefaultHandleMethodChecker extends AbstractHandleMethodChecker {

    private final Set<Class<?>> classes = ReflectionUtils.getSubTypesSuperAnnotation(ProcessUpdate.class);
    private final DefaultMethodCheckConditionFactory conditionFactory;

    public DefaultHandleMethodChecker(DefaultMethodCheckConditionFactory conditionFactory) {
        this.conditionFactory = conditionFactory;
    }

    @Override
    public boolean check(Method method) {
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
        if (AnnotationUtils.hasAnnotation(method, ProcessFile.class, true)) {
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

    private boolean checkAnnotationIsHandleMessage(Method method) {
        return AnnotationUtils.hasAnnotation(method, HandleMessage.class, false);
    }

    private boolean hasProcessFile(Method method, boolean annotationValid) {
        boolean parameterValid;
        int parameterCount = method.getParameterCount();

        if (parameterCount < 2 || parameterCount > 3) {
            throw new InvalidParamsException("There is an error in the method parameters with handle annotations!");
        }

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

        return annotationValid;
    }

    @Override
    public Set<MethodCondition> conditions() {
        return conditionFactory.cachedCreate();
    }

    @Override
    public ProcessType processType() {
        return ProcessType.UPDATE;
    }
}
