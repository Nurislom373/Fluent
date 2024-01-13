package org.khasanof.factories.proxy;

import org.jetbrains.annotations.NotNull;
import org.khasanof.FluentBot;
import org.khasanof.adapter.ExecMethodResponseAdapter;
import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.handler.ExecuteMethodChecker;
import org.khasanof.interceptor.FluentMethodInterceptor;
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
    private final ExecMethodResponseAdapter methodResponseAdapter;

    public DefaultProxyFluentBotFactory(GenericSingleton<FluentBot> fluentBot,
                                        ExecuteMethodChecker proxyMethodHandler,
                                        ExecMethodResponseAdapter methodResponseAdapter) {
        this.fluentBot = fluentBot;
        this.proxyMethodHandler = proxyMethodHandler;
        this.methodResponseAdapter = methodResponseAdapter;
    }

    @Override
    public FluentBot create(MethodInvokeHistory methodInvokeHistory) {
        final var enhancer = new Enhancer();
        enhancer.setSuperclass(FluentBot.class);
        enhancer.setCallback(methodInterceptor(methodInvokeHistory));
        return (FluentBot) enhancer.create();
    }

    private FluentMethodInterceptor methodInterceptor(MethodInvokeHistory methodInvokeHistory) {
        return new FluentMethodInterceptor(fluentBot.getInstance(), proxyMethodHandler, methodInvokeHistory, getInvokeResponseFactory());
    }

    @NotNull
    private DefaultProxyMethodInvokeResponseFactory getInvokeResponseFactory() {
        return new DefaultProxyMethodInvokeResponseFactory(methodResponseAdapter);
    }
}
