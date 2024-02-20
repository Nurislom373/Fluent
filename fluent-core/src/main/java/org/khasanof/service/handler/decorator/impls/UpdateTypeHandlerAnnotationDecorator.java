package org.khasanof.service.handler.decorator.impls;

import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.registry.appropriate.AppropriateTypeRegistryContainer;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 10:27 PM
 */
public class UpdateTypeHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final AppropriateTypeRegistryContainer registryContainer;

    public UpdateTypeHandlerAnnotationDecorator(AppropriateTypeRegistryContainer registryContainer) {
        this.registryContainer = registryContainer;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (Objects.isNull(registry)) {
            return;
        }
        registryContainer.addAppropriateType(registry.getAppropriateUpdateType());
    }
}
