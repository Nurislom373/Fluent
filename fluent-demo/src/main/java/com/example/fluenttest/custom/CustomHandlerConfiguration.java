package com.example.fluenttest.custom;

import com.example.fluenttest.custom.annotation.HandleStickerRegistry;
import jakarta.annotation.PostConstruct;
import org.khasanof.registry.handler.HandlerAnnotationRegistryContainer;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see com.example.fluenttest.custom
 * @since 2/21/2024 12:12 AM
 */
@Configuration
public class CustomHandlerConfiguration {

    private final HandleStickerRegistry handleStickerRegistry;
    private final HandlerAnnotationRegistryContainer handlerAnnotationRegistryContainer;

    public CustomHandlerConfiguration(HandleStickerRegistry handleStickerRegistry,
                                      HandlerAnnotationRegistryContainer handlerAnnotationRegistryContainer) {

        this.handleStickerRegistry = handleStickerRegistry;
        this.handlerAnnotationRegistryContainer = handlerAnnotationRegistryContainer;
    }

    @PostConstruct
    void registrationCustomAnnotation() {
        handlerAnnotationRegistryContainer.addHandlerAnnotationRegistry(handleStickerRegistry);
    }
}
