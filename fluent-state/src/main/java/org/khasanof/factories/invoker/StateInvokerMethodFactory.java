package org.khasanof.factories.invoker;

import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.models.invoker.SimpleInvokerMethod;
import org.khasanof.service.interceptor.invoke.InvokerMethodInterceptorService;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.factories.invoker
 * @since 2/8/2024 10:26 PM
 */
public class StateInvokerMethodFactory implements InvokerMethodFactory {

    private final InvokerMethodInterceptorService interceptorService;

    public StateInvokerMethodFactory(InvokerMethodInterceptorService interceptorService) {
        this.interceptorService = interceptorService;
    }

    @Override
    public SimpleInvoker create(Map.Entry<Method, Object> entry) {
        return create(entry, new HashMap<>());
    }

    @Override
    public SimpleInvoker create(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params) {
        SimpleInvokerMethod simpleInvokerMethod = buildInvoker(entry);
        simpleInvokerMethod.setParams(params);
        fillParams(entry, params);
        return simpleInvokerMethod;
    }

    private void fillParams(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params) {
        interceptorService.intercept(entry, params);
    }

    private SimpleInvokerMethod buildInvoker(Map.Entry<Method, Object> entry) {
        return SimpleInvokerMethod.builder()
                .method(entry.getKey())
                .reference(entry.getValue())
                .hasMethodParams(entry.getKey().getParameterCount() > 0)
                .build();
    }
}
