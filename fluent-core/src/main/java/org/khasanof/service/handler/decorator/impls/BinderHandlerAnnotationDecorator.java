package org.khasanof.service.handler.decorator.impls;

import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.registry.binder.UpdateTypeBinderRegistry;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 3/3/2024 5:48 PM
 */
public class BinderHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final UpdateTypeBinderRegistry updateTypeBinderRegistry;

    public BinderHandlerAnnotationDecorator(FindBeansOfTypeService findBeansOfTypeService,
                                            UpdateTypeBinderRegistry updateTypeBinderRegistry) {

        this.findBeansOfTypeService = findBeansOfTypeService;
        this.updateTypeBinderRegistry = updateTypeBinderRegistry;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (Objects.isNull(registry.getUpdateTypeBinder())) {
            return;
        }
        checkUpdateTypeBinder(registry);
    }

    private void checkUpdateTypeBinder(HandlerAnnotationRegistry registry) {
        findBeansOfTypeService.findBean(registry.getUpdateTypeBinder())
                .ifPresentOrElse(updateTypeBinderRegistry::addUpdateTypeBinder, () -> {
                    throw new RuntimeException("UpdateType binder not found!");
                });
    }
}
