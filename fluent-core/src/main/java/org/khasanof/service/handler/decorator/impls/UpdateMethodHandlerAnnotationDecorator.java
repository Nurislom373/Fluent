package org.khasanof.service.handler.decorator.impls;

import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.registry.appropriate.AppropriateMethodRegistryContainer;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 10:32 PM
 */
@SuppressWarnings({"rawtypes"})
public class UpdateMethodHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final AppropriateMethodRegistryContainer registryContainer;

    public UpdateMethodHandlerAnnotationDecorator(AppropriateMethodRegistryContainer registryContainer) {
        this.registryContainer = registryContainer;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (Objects.isNull(registry.getAppropriateUpdateMethod())) {
            return;
        }
        registryContainer.addAppropriateMethod(registry.getAppropriateUpdateMethod());
    }

    private void checkAppropriateMethod(HandlerAnnotationRegistry registry) {
        AppropriateUpdateMethod appropriateUpdateMethod = registry.getAppropriateUpdateMethod();

        if (Objects.isNull(appropriateUpdateMethod.handleType())) {
            throw new RuntimeException("AppropriateUpdateMethod handleType must not be null!");
        }
    }
}
