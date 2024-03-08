package org.khasanof.factories.proxy;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.proxy
 * @since 1/9/2024 10:52 PM
 */
public interface ProxyMethodInvokeResponseFactory {

    Object createProxyResponse(Method method, Object[] args);

}
