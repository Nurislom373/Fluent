package org.khasanof.feature.interceptor.invoke;

import org.khasanof.feature.interceptor.InvokerMethodInterceptor;
import org.khasanof.mediator.MethodTypeDefinitionMediator;
import org.khasanof.models.invoker.InvokerParam;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.feature.interceptor.invoke
 * @since 2/25/2024 7:47 PM
 */
@Component
public class InvokerMethodDefinitionInterceptor implements InvokerMethodInterceptor {

    private final MethodTypeDefinitionMediator methodTypeDefinitionMediator;

    public InvokerMethodDefinitionInterceptor(MethodTypeDefinitionMediator methodTypeDefinitionMediator) {
        this.methodTypeDefinitionMediator = methodTypeDefinitionMediator;
    }

    @Override
    public void preHandle(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params) {
        params.put(InvokerParam.METHOD_TYPE, methodTypeDefinitionMediator.definition(entry.getKey()));
    }
}
