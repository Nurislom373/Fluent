package org.khasanof.factories.proxy;

import org.khasanof.mediator.ExecMethodResponseMediator;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.proxy
 * @since 1/9/2024 10:52 PM
 */
public class DefaultProxyMethodInvokeResponseFactory implements ProxyMethodInvokeResponseFactory {

    private final ExecMethodResponseMediator methodResponseAdapter;

    public DefaultProxyMethodInvokeResponseFactory(ExecMethodResponseMediator methodResponseAdapter) {
        this.methodResponseAdapter = methodResponseAdapter;
    }

    @Override
    public Object createProxyResponse(Method method, Object[] args) {
        return methodResponseAdapter.createResponse(method, args);
    }
}
