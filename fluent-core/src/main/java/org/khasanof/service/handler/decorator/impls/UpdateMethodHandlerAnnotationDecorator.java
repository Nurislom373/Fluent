package org.khasanof.service.handler.decorator.impls;

import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.registry.appropriate.AppropriateMethodRegistryContainer;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 10:32 PM
 */
@SuppressWarnings({"rawtypes"})
public class UpdateMethodHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final AppropriateMethodRegistryContainer registryContainer;

    public UpdateMethodHandlerAnnotationDecorator(FindBeansOfTypeService findBeansOfTypeService,
                                                  AppropriateMethodRegistryContainer registryContainer) {

        this.findBeansOfTypeService = findBeansOfTypeService;
        this.registryContainer = registryContainer;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (Objects.isNull(registry.getAppropriateUpdateMethod())) {
            throw new RuntimeException("appropriate method is null!");
        }
        AppropriateUpdateMethod appropriateUpdateMethod = getAppropriateUpdateMethod(registry);
        registryContainer.addAppropriateMethod(appropriateUpdateMethod);
    }

    private AppropriateUpdateMethod getAppropriateUpdateMethod(HandlerAnnotationRegistry registry) {
        return findBeansOfTypeService.findBean(registry.getAppropriateUpdateMethod())
                .orElseThrow(() -> new RuntimeException("AppropriateUpdateMethod instance not found!"));
    }
}
