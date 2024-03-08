package org.khasanof.service.runnable.annotation;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.enums.HandleAnnotation;
import org.khasanof.registry.annotation.AnnotationHandlerRegistryContainer;
import org.khasanof.service.runnable.PostProcessor;

import java.util.EnumSet;
import java.util.HashSet;

/**
 * @author Nurislom
 * @see org.khasanof.service.runnable.annotation
 * @since 2/18/2024 5:44 PM
 */
@Slf4j
//@Service
public class AnnotationHandlerRegistryPostProcessor implements PostProcessor {

    private final AnnotationHandlerRegistryContainer registryContainer;

    public AnnotationHandlerRegistryPostProcessor(AnnotationHandlerRegistryContainer registryContainer) {
        this.registryContainer = registryContainer;
    }

    @Override
    public void run() {
        // registering default annotation handlers
        log.debug("registering default annotation handlers...");
        registryContainer.addAnnotationHandlers(new HashSet<>(EnumSet.allOf(HandleAnnotation.class)));
    }
}
