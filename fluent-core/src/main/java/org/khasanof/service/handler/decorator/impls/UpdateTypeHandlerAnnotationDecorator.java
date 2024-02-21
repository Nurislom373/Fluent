package org.khasanof.service.handler.decorator.impls;

import org.khasanof.executors.appropriate.AppropriateUpdateType;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.registry.appropriate.AppropriateTypeRegistryContainer;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 10:27 PM
 */
public class UpdateTypeHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final AppropriateTypeRegistryContainer registryContainer;

    public UpdateTypeHandlerAnnotationDecorator(FindBeansOfTypeService findBeansOfTypeService,
                                                AppropriateTypeRegistryContainer registryContainer) {

        this.findBeansOfTypeService = findBeansOfTypeService;
        this.registryContainer = registryContainer;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (Objects.isNull(registry)) {
            throw new RuntimeException("AppropriateUpdateType must not be null!");
        }
        AppropriateUpdateType appropriateUpdateType = getAppropriateUpdateType(registry);
        registryContainer.addAppropriateType(appropriateUpdateType);
    }

    private AppropriateUpdateType getAppropriateUpdateType(HandlerAnnotationRegistry registry) {
        return findBeansOfTypeService.findBean(registry.getAppropriateUpdateType())
                .orElseThrow(() -> new RuntimeException("AppropriateUpdateType instance not found!"));
    }
}
