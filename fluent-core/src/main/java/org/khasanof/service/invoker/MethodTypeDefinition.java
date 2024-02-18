package org.khasanof.service.invoker;

import org.khasanof.constants.FluentConstants;
import org.khasanof.enums.DefaultMethodType;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 1/27/2024 8:37 PM
 */
public interface MethodTypeDefinition {

    boolean isMatch(Method method);

    DefaultMethodType methodType();

    default int getOrder() {
        return FluentConstants.DEFAULT_ORDER;
    }
}
