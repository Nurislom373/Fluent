package org.khasanof.inline;

import jakarta.annotation.PostConstruct;
import org.khasanof.registry.handler.HandlerAnnotationRegistryContainer;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.inline
 * @since 3/3/2024 6:17 PM
 */
@Configuration
public class InlineQueryAnnotationConfiguration {

    private final HandleInlineQueryRegistry handleInlineQueryRegistry;
    private final HandlerAnnotationRegistryContainer handlerAnnotationRegistryContainer;

    public InlineQueryAnnotationConfiguration(HandleInlineQueryRegistry handleInlineQueryRegistry,
                                              HandlerAnnotationRegistryContainer handlerAnnotationRegistryContainer) {

        this.handleInlineQueryRegistry = handleInlineQueryRegistry;
        this.handlerAnnotationRegistryContainer = handlerAnnotationRegistryContainer;
    }

    @PostConstruct
    void registration() {
        handlerAnnotationRegistryContainer.addHandlerAnnotationRegistry(handleInlineQueryRegistry);
    }
}
