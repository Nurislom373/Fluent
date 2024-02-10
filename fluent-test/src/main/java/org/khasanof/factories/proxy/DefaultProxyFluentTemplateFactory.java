package org.khasanof.factories.proxy;

import org.jetbrains.annotations.NotNull;
import org.khasanof.adapter.ExecMethodResponseAdapter;
import org.khasanof.handler.ExecuteMethodChecker;
import org.khasanof.interceptor.FluentMethodInterceptor;
import org.khasanof.memento.MethodInvokeHistory;
import org.khasanof.service.template.FluentTemplate;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 1/9/2024 12:47 AM
 */
public class DefaultProxyFluentTemplateFactory implements ProxyFluentTemplateFactory {

    private final ExecuteMethodChecker proxyMethodHandler;
    private final ExecMethodResponseAdapter methodResponseAdapter;

    public DefaultProxyFluentTemplateFactory(ExecuteMethodChecker proxyMethodHandler, ExecMethodResponseAdapter methodResponseAdapter) {
        this.proxyMethodHandler = proxyMethodHandler;
        this.methodResponseAdapter = methodResponseAdapter;
    }

    @Override
    public FluentTemplate create(MethodInvokeHistory methodInvokeHistory) {
        final var enhancer = new Enhancer();
        enhancer.setSuperclass(FluentTemplate.class);
        enhancer.setCallback(methodInterceptor(methodInvokeHistory));
        return (FluentTemplate) enhancer.create();
    }

    private FluentMethodInterceptor methodInterceptor(MethodInvokeHistory methodInvokeHistory) {
        return new FluentMethodInterceptor(proxyMethodHandler, methodInvokeHistory, getInvokeResponseFactory());
    }

    @NotNull
    private DefaultProxyMethodInvokeResponseFactory getInvokeResponseFactory() {
        return new DefaultProxyMethodInvokeResponseFactory(methodResponseAdapter);
    }
}
