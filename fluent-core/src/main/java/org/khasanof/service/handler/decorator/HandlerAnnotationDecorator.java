package org.khasanof.service.handler.decorator;

import org.khasanof.feature.annotation.HandlerAnnotationRegistry;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator
 * @since 2/19/2024 11:21 PM
 */
public interface HandlerAnnotationDecorator {

    /**
     *
     * @param registry
     */
    void execute(HandlerAnnotationRegistry registry);
}
