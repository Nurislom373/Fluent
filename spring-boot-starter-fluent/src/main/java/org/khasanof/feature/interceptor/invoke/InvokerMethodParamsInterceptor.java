package org.khasanof.feature.interceptor.invoke;

import org.khasanof.feature.interceptor.InvokerMethodInterceptor;
import org.khasanof.models.invoker.InvokerParam;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.feature.interceptor.invoke
 * @since 2/25/2024 8:12 PM
 */
@Component
public class InvokerMethodParamsInterceptor implements InvokerMethodInterceptor {

    @Override
    public void preHandle(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params) {
        internalPreHandle(params, entry.getKey());
    }

    private void internalPreHandle(Map<InvokerParam, Object> params, Method method) {
        if (method.getParameterCount() > 0) {
            params.put(InvokerParam.METHOD_PARAMETER_TYPES, Arrays.asList(method.getParameterTypes()));
        }
    }
}
