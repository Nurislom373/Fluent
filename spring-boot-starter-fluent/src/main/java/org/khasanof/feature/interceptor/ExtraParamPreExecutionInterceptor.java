package org.khasanof.feature.interceptor;

import org.khasanof.enums.MethodType;
import org.khasanof.feature.HandleMethodExtraParam;
import org.khasanof.feature.PreExecutionInterceptor;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.service.FindBeansOfTypeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.feature.interceptor
 * @since 2/1/2024 10:47 PM
 */
@Component
public class ExtraParamPreExecutionInterceptor implements PreExecutionInterceptor, InitializingBean {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final Map<MethodType, HandleMethodExtraParam> handleMethodCustomParamMap = new HashMap<>();

    public ExtraParamPreExecutionInterceptor(FindBeansOfTypeService findBeansOfTypeService) {
        this.findBeansOfTypeService = findBeansOfTypeService;
    }

    @Override
    public void preHandle(SimpleInvoker simpleInvoker) {
        MethodType methodType = getMethodType(simpleInvoker);

        if (handleMethodCustomParamMap.containsKey(methodType)) {
            handleMethodCustomParamMap.get(methodType)
                    .execute(simpleInvoker);
        }
    }

    private MethodType getMethodType(SimpleInvoker simpleInvoker) {
        return (MethodType) simpleInvoker.getParams().get(InvokerParam.METHOD_TYPE);
    }

    @Override
    public void afterPropertiesSet() {
        findBeansOfTypeService.findAllByList(HandleMethodExtraParam.class)
                .forEach(handleMethodExtraParam -> handleMethodCustomParamMap.put(handleMethodExtraParam.methodType(), handleMethodExtraParam));
    }
}
