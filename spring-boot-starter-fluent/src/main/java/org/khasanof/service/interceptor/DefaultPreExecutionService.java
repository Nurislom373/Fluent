package org.khasanof.service.interceptor;

import org.khasanof.feature.PreExecutionInterceptor;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.service.FindBeansOfTypeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.service.interceptor
 * @since 2/1/2024 11:15 PM
 */
@Service
public class DefaultPreExecutionService implements PreExecutionService, InitializingBean {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final List<PreExecutionInterceptor> preExecutionInterceptors = new ArrayList<>();

    public DefaultPreExecutionService(FindBeansOfTypeService findBeansOfTypeService) {
        this.findBeansOfTypeService = findBeansOfTypeService;
    }

    @Override
    public void preHandle(SimpleInvoker simpleInvoker) {
        preExecutionInterceptors.forEach(preExecutionInterceptor -> preExecutionInterceptor.preHandle(simpleInvoker));
    }

    @Override
    public void afterPropertiesSet() {
        preExecutionInterceptors.addAll(findBeansOfTypeService.findAllByList(PreExecutionInterceptor.class));
    }
}
