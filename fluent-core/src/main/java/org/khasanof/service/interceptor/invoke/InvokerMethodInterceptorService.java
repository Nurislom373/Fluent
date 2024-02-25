package org.khasanof.service.interceptor.invoke;

import org.khasanof.feature.interceptor.InvokerMethodInterceptor;
import org.khasanof.models.invoker.InvokerParam;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.service.interceptor
 * @since 2/25/2024 8:07 PM
 */
public interface InvokerMethodInterceptorService {

    /**
     *
     * @param entry
     * @param params
     */
    void intercept(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params);

    /**
     *
     * @param interceptors
     */
    void addInterceptors(List<InvokerMethodInterceptor> interceptors);

    /**
     *
     * @param interceptor
     */
    void addInterceptor(InvokerMethodInterceptor interceptor);
}
