package org.khasanof.factories.proxy;

import org.khasanof.adapter.ExecMethodResponseAdapter;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.proxy
 * @since 1/9/2024 10:52 PM
 */
public class DefaultProxyMethodInvokeResponseFactory implements ProxyMethodInvokeResponseFactory {

    private final ExecMethodResponseAdapter methodResponseAdapter;

    public DefaultProxyMethodInvokeResponseFactory(ExecMethodResponseAdapter methodResponseAdapter) {
        this.methodResponseAdapter = methodResponseAdapter;
    }

    @Override
    public Object createProxyResponse(Method method, Object[] args) {
        return methodResponseAdapter.createResponse(method, args);
    }
}
