package org.khasanof.service;

import org.khasanof.memento.MethodInvokeHistory;
import org.khasanof.service.template.FluentTemplate;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 2/10/2024 9:58 PM
 */
public interface ProxyFluentTemplateService {

    FluentTemplate getFluentTemplate();

    MethodInvokeHistory getInvokeHistory();
}
