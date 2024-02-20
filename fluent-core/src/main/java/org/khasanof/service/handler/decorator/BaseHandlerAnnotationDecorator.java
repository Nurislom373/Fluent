package org.khasanof.service.handler.decorator;

import org.khasanof.feature.annotation.HandlerAnnotationRegistry;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator
 * @since 2/19/2024 11:20 PM
 */
public abstract class BaseHandlerAnnotationDecorator implements HandlerAnnotationDecorator {

    private HandlerAnnotationDecorator handlerAnnotationDecorator;

    public BaseHandlerAnnotationDecorator(HandlerAnnotationDecorator handlerAnnotationDecorator) {
        this.handlerAnnotationDecorator = handlerAnnotationDecorator;
    }

    public BaseHandlerAnnotationDecorator() {
    }

    public BaseHandlerAnnotationDecorator decorator(HandlerAnnotationDecorator handlerAnnotationDecorator) {
        this.handlerAnnotationDecorator = handlerAnnotationDecorator;
        return this;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        if (Objects.nonNull(handlerAnnotationDecorator)) {
            handlerAnnotationDecorator.execute(registry);
        }
    }
}
