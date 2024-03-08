package org.khasanof.service.handler.decorator.impls;

import org.khasanof.executors.execution.Perform;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 10:44 PM
 */
public class PerformHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final FindBeansOfTypeService findBeansOfTypeService;

    public PerformHandlerAnnotationDecorator(FindBeansOfTypeService findBeansOfTypeService) {
        this.findBeansOfTypeService = findBeansOfTypeService;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (Objects.isNull(registry.getPerform())) {
            return;
        }
        checkPerform(registry);
    }

    private void checkPerform(HandlerAnnotationRegistry registry) {
        Perform perform = getPerform(registry);

        if (Objects.isNull(perform.getType())) {
            throw new RuntimeException("Perform MethodType is null!");
        }

        if (!Objects.equals(perform.getType(), registry.getMethodType())) {
            throw new RuntimeException("Perform MethodType not equal!");
        }
    }

    private Perform getPerform(HandlerAnnotationRegistry registry) {
        return findBeansOfTypeService.findBean(registry.getPerform())
                .orElseThrow(() -> new RuntimeException("Perform instance not found!"));
    }
}
