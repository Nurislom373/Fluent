package org.khasanof.service.handler.decorator.impls;

import org.khasanof.feature.HandleMethodExtraParam;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 3/3/2024 5:01 PM
 */
public class ExtraHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final FindBeansOfTypeService findBeansOfTypeService;

    public ExtraHandlerAnnotationDecorator(FindBeansOfTypeService findBeansOfTypeService) {
        this.findBeansOfTypeService = findBeansOfTypeService;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (Objects.isNull(registry.getMethodExtraParam())) {
            return;
        }
        checkMethodExtraParam(registry);
    }

    private void checkMethodExtraParam(HandlerAnnotationRegistry registry) {
        findBeansOfTypeService.findBean(registry.getMethodExtraParam())
                .ifPresentOrElse(handleMethodExtraParam -> {
                    if (!Objects.equals(handleMethodExtraParam.methodType(), registry.getMethodType())) {
                        throw new RuntimeException("HandleMethodExtraParam methodType not equal to registry methodType!");
                    }
                }, () -> {
                    throw new RuntimeException("HandleMethodExtraParam bean not found!");
                });
    }
}
