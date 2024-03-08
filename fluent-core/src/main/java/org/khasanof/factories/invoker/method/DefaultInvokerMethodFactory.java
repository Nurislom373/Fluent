package org.khasanof.factories.invoker.method;

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
 * @since 12/16/2023 9:14 PM
 */
public class DefaultInvokerMethodFactory implements InvokerMethodFactory {

    private final InvokerMethodInterceptorService interceptorService;

    public DefaultInvokerMethodFactory(InvokerMethodInterceptorService interceptorService) {
        this.interceptorService = interceptorService;
    }

    /**
     *
     * @param entry
     * @return
     */
    @Override
    public SimpleInvoker create(Map.Entry<Method, Object> entry) {
        return create(entry, new HashMap<>());
    }

    /**
     *
     * @param entry
     * @param params
     * @return
     */
    @Override
    public SimpleInvoker create(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params) {
        SimpleInvokerMethod simpleInvokerMethod = buildInvoker(entry);
        simpleInvokerMethod.setParams(params);
        fillParams(entry, simpleInvokerMethod);
        return simpleInvokerMethod;
    }

    private SimpleInvokerMethod buildInvoker(Map.Entry<Method, Object> entry) {
        return SimpleInvokerMethod.builder()
                .method(entry.getKey())
                .reference(entry.getValue())
                .hasMethodParams(entry.getKey().getParameterCount() > 0)
                .build();
    }

    private void fillParams(Map.Entry<Method, Object> entry, SimpleInvokerMethod simpleInvokerMethod) {
        interceptorService.intercept(entry, simpleInvokerMethod.getParams());
    }
}
