package org.khasanof.collector.method.checker;

import org.khasanof.constants.ParamConstants;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/26/2023 10:20 PM
 */
public abstract class AbstractHandleMethodChecker implements ProcessTypeHandleMethodChecker {

    protected final Class<?>[] MAIN_PARAMS = ParamConstants.DEFAULT_HANDLER_PARAM;

    protected boolean paramsTypeCheckV2(Class<?>[] methodParams, Class<?>[] matchParams) {
        return Arrays.stream(matchParams)
                .allMatch(param -> Arrays.asList(methodParams)
                        .contains(param));
    }

    protected boolean paramsTypeCheckV3(Class<?>[] methodParams, Class<?>[] matchParams) {
        return Arrays.stream(matchParams)
                .allMatch(param -> Arrays.stream(methodParams)
                        .anyMatch(methodParam -> param.equals(methodParam) ||
                                param.isAssignableFrom(methodParam)));
    }

    protected boolean hasAnnotation(Method method, Class<? extends Annotation> annotation) {
        return method.isAnnotationPresent(annotation);
    }

}
