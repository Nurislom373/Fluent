package org.khasanof.service.interceptor.invoke;

import org.khasanof.feature.interceptor.InvokerMethodInterceptor;
import org.khasanof.models.invoker.InvokerParam;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.service.interceptor.invoke
 * @since 2/25/2024 8:07 PM
 */
public class DefaultInvokerMethodInterceptorService implements InvokerMethodInterceptorService {

    private final Set<InvokerMethodInterceptor> interceptors = new HashSet<>();

    @Override
    public void intercept(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params) {
        interceptors.forEach(interceptor -> interceptor.preHandle(entry, params));
    }

    @Override
    public void addInterceptors(List<InvokerMethodInterceptor> interceptors) {
        if (Objects.nonNull(interceptors)) {
            this.interceptors.addAll(interceptors);
        }
    }

    @Override
    public void addInterceptor(InvokerMethodInterceptor interceptor) {
        if (Objects.nonNull(interceptor)) {
            this.interceptors.add(interceptor);
        }
    }
}
