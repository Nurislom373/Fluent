package org.khasanof.factories.response;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response
 * @since 1/13/2024 11:29 PM
 */
public abstract class ExecMethodResponseDecorator implements ExecMethodResponse {

    protected final ExecMethodResponse executeMethodResponse;

    protected ExecMethodResponseDecorator(ExecMethodResponse executeMethodResponse) {
        this.executeMethodResponse = executeMethodResponse;
    }

    @Override
    public Object createResponse(Method method, Object[] args) throws Exception {
        return executeMethodResponse.createResponse(method, args);
    }
}
