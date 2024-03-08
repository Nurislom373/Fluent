package org.khasanof.feature.interceptor;

import org.khasanof.models.invoker.SimpleInvoker;

/**
 * @author Nurislom
 * @see org.khasanof.feature.interceptor
 * @since 2/27/2024 10:37 PM
 */
public interface PostFindInvokerInterceptor {

    /**
     *
     * @param simpleInvoker
     * @return
     */
    boolean postHandle(SimpleInvoker simpleInvoker);
}
