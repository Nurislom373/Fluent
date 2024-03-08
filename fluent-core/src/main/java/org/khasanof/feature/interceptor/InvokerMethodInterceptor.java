package org.khasanof.feature.interceptor;

import org.khasanof.models.invoker.InvokerParam;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.feature.interceptor
 * @since 2/25/2024 7:45 PM
 */
public interface InvokerMethodInterceptor {

    /**
     *
     * @param entry
     * @param params
     */
    void preHandle(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params);
}
