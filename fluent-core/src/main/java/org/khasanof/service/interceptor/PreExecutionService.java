package org.khasanof.service.interceptor;

import org.khasanof.models.invoker.SimpleInvoker;

/**
 * @author Nurislom
 * @see org.khasanof.service.interceptor
 * @since 2/1/2024 10:16 PM
 */
public interface PreExecutionService {

    void preHandle(SimpleInvoker simpleInvoker);

}
