package org.khasanof.feature.interceptor;

import org.khasanof.models.invoker.SimpleInvoker;

/**
 * @author Nurislom
 * @see org.khasanof.feature
 * @since 2/1/2024 10:10 PM
 */
public interface PreExecutionInterceptor {

    /**
     *
     * @param simpleInvoker
     */
    boolean preHandle(SimpleInvoker simpleInvoker);
}
