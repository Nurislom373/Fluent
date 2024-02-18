package org.khasanof.config.service.annotation;

import org.khasanof.enums.HandleAnnotation;
import org.khasanof.registry.annotation.AnnotationHandlerRegistryContainer;
import org.khasanof.registry.annotation.DefaultAnnotationHandlerRegistryContainer;
import org.khasanof.service.annotation.handler.AnnotationHandlerService;
import org.khasanof.service.annotation.handler.DefaultAnnotationHandlerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumSet;
import java.util.HashSet;

/**
 * @author Nurislom
 * @see org.khasanof.config.service.annotation
 * @since 2/18/2024 5:36 PM
 */
@Configuration
public class AnnotationHandlerAutoConfiguration {

    /**
     *
     * @return {@link AnnotationHandlerRegistryContainer} bean
     */
    @Bean
    public AnnotationHandlerRegistryContainer annotationHandlerRegistryContainer() {
        DefaultAnnotationHandlerRegistryContainer registryContainer = new DefaultAnnotationHandlerRegistryContainer();
        registryContainer.addAnnotationHandlers(new HashSet<>(EnumSet.allOf(HandleAnnotation.class)));
        return registryContainer;
    }

    /**
     *
     * @param registryContainer bean
     * @return {@link AnnotationHandlerService} bean
     */
    @Bean
    public AnnotationHandlerService annotationHandlerService(AnnotationHandlerRegistryContainer registryContainer) {
        return new DefaultAnnotationHandlerService(registryContainer);
    }
}
