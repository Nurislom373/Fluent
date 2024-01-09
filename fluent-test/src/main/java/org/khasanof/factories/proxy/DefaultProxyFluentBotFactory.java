package org.khasanof.factories.proxy;

import org.khasanof.FluentBot;
import org.khasanof.factories.response.DefaultExecuteResponseFactory;
import org.khasanof.interceptor.ProxyFluentMethodInterceptor;
import org.khasanof.handler.ProxyMethodHandler;
import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.memento.MethodInvokeHistory;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 1/9/2024 12:47 AM
 */
public class DefaultProxyFluentBotFactory implements ProxyFluentBotFactory {

    private final GenericSingleton<FluentBot> fluentBot;
    private final ProxyMethodHandler proxyMethodHandler;

    public DefaultProxyFluentBotFactory(GenericSingleton<FluentBot> fluentBot,
                                        ProxyMethodHandler proxyMethodHandler) {
        this.fluentBot = fluentBot;
        this.proxyMethodHandler = proxyMethodHandler;
    }

    @Override
    public FluentBot create(MethodInvokeHistory methodInvokeHistory) {
        final var enhancer = new Enhancer();
        enhancer.setSuperclass(FluentBot.class);
        enhancer.setCallback(methodInterceptor(methodInvokeHistory));
        return (FluentBot) enhancer.create();
    }

    private ProxyFluentMethodInterceptor methodInterceptor(MethodInvokeHistory methodInvokeHistory) {
        return new ProxyFluentMethodInterceptor(fluentBot.getInstance(), proxyMethodHandler, methodInvokeHistory,
                new DefaultProxyMethodInvokeResponseFactory(new DefaultExecuteResponseFactory()));
    }
}
