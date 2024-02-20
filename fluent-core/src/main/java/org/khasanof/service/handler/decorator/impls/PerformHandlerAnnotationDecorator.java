package org.khasanof.service.handler.decorator.impls;

import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 10:44 PM
 */
public class PerformHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

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
        if (Objects.isNull(registry.getPerform())) {
            throw new RuntimeException("Perform is null!");
        }

        if (Objects.isNull(registry.getPerform().getType())) {
            throw new RuntimeException("Perform MethodType is null!");
        }

        if (!Objects.equals(registry.getPerform().getType(), registry.getMethodType())) {
            throw new RuntimeException("Perform MethodType not equal!");
        }
    }
}
