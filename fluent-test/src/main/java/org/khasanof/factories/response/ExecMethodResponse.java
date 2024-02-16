package org.khasanof.factories.response;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response
 * @since 1/13/2024 11:27 PM
 */
public interface ExecMethodResponse {

    Object createResponse(Method method, Object[] args) throws Exception;

    default Method getMethod() {
        return null;
    }

    Class<?> returnType();
}
