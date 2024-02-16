package org.khasanof.factories.response.methods;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response.methods
 * @since 2/11/2024 9:13 PM
 */
public class ReturnBooleanMethodsResponse extends AbstractExecMethodResponse {

    @Override
    public Object createResponse(Method method, Object[] args) {
        return Boolean.TRUE;
    }

    @Override
    public Class<?> returnType() {
        return Boolean.class;
    }
}
