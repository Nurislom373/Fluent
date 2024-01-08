package org.khasanof;

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

    public ProxyFluentMethodInterceptor(FluentBot fluentBot,
                                        ProxyMethodHandler proxyMethodHandler,
                                        MethodInvokeHistory methodInvokeHistory) {
        this.fluentBot = fluentBot;
        this.proxyMethodHandler = proxyMethodHandler;
        this.methodInvokeHistory = methodInvokeHistory;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (proxyMethodHandler.isExecuteMethod(method)) {
            addMemento(method, args, proxy);
        }
        return method.invoke(fluentBot, args);
    }

    private void addMemento(Method method, Object[] args, MethodProxy methodProxy) {
        MethodInvokeMemento methodInvokeMemento = new MethodInvokeMemento(method, args, methodProxy);
        methodInvokeHistory.addHistory(methodInvokeMemento);
    }

}
