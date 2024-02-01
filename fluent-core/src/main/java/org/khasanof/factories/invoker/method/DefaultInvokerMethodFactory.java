package org.khasanof.factories.invoker.method;

import org.khasanof.mediator.MethodTypeDefinitionMediator;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.models.invoker.SimpleInvokerMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.factories.invoker
 * @since 12/16/2023 9:14 PM
 */
@Component
public class DefaultInvokerMethodFactory implements InvokerMethodFactory {

    private final MethodTypeDefinitionMediator methodTypeDefinitionMediator;

    public DefaultInvokerMethodFactory(MethodTypeDefinitionMediator methodTypeDefinitionMediator) {
        this.methodTypeDefinitionMediator = methodTypeDefinitionMediator;
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
        putParamsMethodType(entry, simpleInvokerMethod.getParams());
        checkMethodParams(entry, simpleInvokerMethod.getParams());
    }

    private void putParamsMethodType(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params) {
        params.put(InvokerParam.METHOD_TYPE, methodTypeDefinitionMediator.definition(entry.getKey()));
    }

    private void checkMethodParams(Map.Entry<Method, Object> entry, Map<InvokerParam, Object> params) {
        Method method = entry.getKey();

        if (method.getParameterCount() > 0) {
            params.put(InvokerParam.METHOD_PARAMETER_TYPES, Arrays.asList(method.getParameterTypes()));
        }
    }
}
