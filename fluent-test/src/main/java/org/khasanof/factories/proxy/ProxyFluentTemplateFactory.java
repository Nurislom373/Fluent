package org.khasanof.factories.proxy;

import org.khasanof.memento.MethodInvokeHistory;
import org.khasanof.service.template.FluentTemplate;

/**
 * @author Nurislom
 * @see org.khasanof.verifier
 * @since 1/9/2024 12:47 AM
 */
public interface ProxyFluentTemplateFactory {

    FluentTemplate create(MethodInvokeHistory methodInvokeHistory);
}
