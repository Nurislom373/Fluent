package org.khasanof.service.handler.decorator.impls;

import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 10:53 PM
 */
public class CheckerHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (Objects.isNull(registry.getHandleMethodChecker())) {
            return;
        }
        checkHandleMethodChecker(registry);
    }

    private void checkHandleMethodChecker(HandlerAnnotationRegistry registry) {
    }
}
