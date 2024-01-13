package org.khasanof.interceptor;

import org.khasanof.FluentBot;
import org.khasanof.handler.ExecuteMethodChecker;
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
public class FluentMethodInterceptor implements MethodInterceptor {

    private final FluentBot fluentBot;
    private final ExecuteMethodChecker executeMethodChecker;
    private final MethodInvokeHistory methodInvokeHistory;
    private final ProxyMethodInvokeResponseFactory invokeResponseFactory;

    public FluentMethodInterceptor(FluentBot fluentBot,
                                   ExecuteMethodChecker executeMethodChecker,
                                   MethodInvokeHistory methodInvokeHistory,
                                   ProxyMethodInvokeResponseFactory proxyMethodInvokeResponseAdapter) {
        this.fluentBot = fluentBot;
        this.executeMethodChecker = executeMethodChecker;
        this.methodInvokeHistory = methodInvokeHistory;
        this.invokeResponseFactory = proxyMethodInvokeResponseAdapter;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {
        if (executeMethodChecker.isExecuteMethod(method)) {
            addMemento(method, args, proxy);
        }
        return invokeResponseFactory.createProxyResponse(method, args);
    }

    private void addMemento(Method method, Object[] args, MethodProxy methodProxy) {
        MethodInvokeMemento methodInvokeMemento = new MethodInvokeMemento(method, args, methodProxy);
        methodInvokeHistory.addHistory(methodInvokeMemento);
    }

}
