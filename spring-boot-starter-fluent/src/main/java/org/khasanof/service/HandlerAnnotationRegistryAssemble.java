package org.khasanof.service;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.AssembleMethods;
import org.khasanof.constants.FluentConstants;
import org.khasanof.service.handler.HandlerAnnotationRegistryService;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 2/21/2024 12:23 AM
 */
@Slf4j
@Service
public class HandlerAnnotationRegistryAssemble implements AssembleMethods {

    private final HandlerAnnotationRegistryService handlerAnnotationRegistryService;

    public HandlerAnnotationRegistryAssemble(HandlerAnnotationRegistryService handlerAnnotationRegistryService) {
        this.handlerAnnotationRegistryService = handlerAnnotationRegistryService;
    }

    @Override
    public void assemble() {
        log.info("Custom Annotation Assemble Started!");
        handlerAnnotationRegistryService.registry();
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
