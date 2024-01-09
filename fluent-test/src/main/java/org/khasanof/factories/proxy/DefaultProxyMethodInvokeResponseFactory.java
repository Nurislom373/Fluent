package org.khasanof.factories.proxy;

import org.khasanof.factories.response.ExecuteResponseFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * @author Nurislom
 * @see org.khasanof.adapter
 * @since 1/9/2024 10:52 PM
 */
public class DefaultProxyMethodInvokeResponseFactory implements ProxyMethodInvokeResponseFactory {

    private final ExecuteResponseFactory executeResponseFactory;

    public DefaultProxyMethodInvokeResponseFactory(ExecuteResponseFactory executeResponseFactory) {
        this.executeResponseFactory = executeResponseFactory;
    }

    @Override
    public Object create(Object[] objects) {
        return null;
    }

    private Object createInternal(Object[] args) {
        if (args.length == 1) {
            Object arg = args[0];
            if (arg instanceof SendMessage message) {
                return executeResponseFactory.create(message);
            }
        }
        return null;
    }


}
