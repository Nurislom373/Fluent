package org.khasanof.factories.proxy;

import org.khasanof.FluentBot;
import org.khasanof.factories.response.DefaultExecuteResponseFactory;
import org.khasanof.interceptor.FluentMethodInterceptor;
import org.khasanof.handler.ExecuteMethodChecker;
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
    private final ExecuteMethodChecker proxyMethodHandler;

    public DefaultProxyFluentBotFactory(GenericSingleton<FluentBot> fluentBot,
                                        ExecuteMethodChecker proxyMethodHandler) {
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

    private FluentMethodInterceptor methodInterceptor(MethodInvokeHistory methodInvokeHistory) {
        return new FluentMethodInterceptor(fluentBot.getInstance(), proxyMethodHandler, methodInvokeHistory,
                new DefaultProxyMethodInvokeResponseFactory(new DefaultExecuteResponseFactory()));
    }
}
