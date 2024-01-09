package org.khasanof.factories.proxy;

import org.khasanof.FluentBot;
import org.khasanof.memento.MethodInvokeHistory;

/**
 * @author Nurislom
 * @see org.khasanof.verifier
 * @since 1/9/2024 12:47 AM
 */
public interface ProxyFluentBotFactory {

    FluentBot create(MethodInvokeHistory methodInvokeHistory);

}
