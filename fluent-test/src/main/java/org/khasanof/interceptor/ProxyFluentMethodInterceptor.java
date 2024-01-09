package org.khasanof.interceptor;

import org.khasanof.FluentBot;
import org.khasanof.handler.ProxyMethodHandler;
import org.khasanof.factories.proxy.ProxyMethodInvokeResponseFactory;
import org.khasanof.memento.MethodInvokeHistory;
import org.khasanof.memento.MethodInvokeMemento;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/7/2024 3:03 PM
 */
public class ProxyFluentMethodInterceptor implements MethodInterceptor {

    private final FluentBot fluentBot;
    private final ProxyMethodHandler proxyMethodHandler;
    private final MethodInvokeHistory methodInvokeHistory;
    private final ProxyMethodInvokeResponseFactory invokeResponseFactory;

    public ProxyFluentMethodInterceptor(FluentBot fluentBot,
                                        ProxyMethodHandler proxyMethodHandler,
                                        MethodInvokeHistory methodInvokeHistory,
                                        ProxyMethodInvokeResponseFactory proxyMethodInvokeResponseAdapter) {
        this.fluentBot = fluentBot;
        this.proxyMethodHandler = proxyMethodHandler;
        this.methodInvokeHistory = methodInvokeHistory;
        this.invokeResponseFactory = proxyMethodInvokeResponseAdapter;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (proxyMethodHandler.isExecuteMethod(method)) {
            addMemento(method, args, proxy);
        }
        return invokeResponseFactory.create(args);
    }

    private void addMemento(Method method, Object[] args, MethodProxy methodProxy) {
        MethodInvokeMemento methodInvokeMemento = new MethodInvokeMemento(method, args, methodProxy);
        methodInvokeHistory.addHistory(methodInvokeMemento);
    }

}
