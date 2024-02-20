package org.khasanof.service.handler;

import org.khasanof.feature.annotation.HandlerAnnotationRegistry;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler
 * @since 2/19/2024 10:54 PM
 */
public interface HandlerAnnotationDefinitionService {

    /**
     *
     * @param registry
     * @return
     */
    HandlerAnnotationDefinition definition(HandlerAnnotationRegistry registry);
}
