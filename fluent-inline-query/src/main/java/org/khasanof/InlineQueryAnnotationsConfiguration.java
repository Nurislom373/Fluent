package org.khasanof;

import jakarta.annotation.PostConstruct;
import org.khasanof.inline.HandleChosenInlineQueryRegistry;
import org.khasanof.inline.HandleInlineQueryRegistry;
import org.khasanof.registry.handler.HandlerAnnotationRegistryContainer;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.inline
 * @since 3/3/2024 6:17 PM
 */
@Configuration
public class InlineQueryAnnotationsConfiguration {

    private final HandleInlineQueryRegistry handleInlineQueryRegistry;
    private final HandleChosenInlineQueryRegistry handleChosenInlineQueryRegistry;
    private final HandlerAnnotationRegistryContainer handlerAnnotationRegistryContainer;

    public InlineQueryAnnotationsConfiguration(HandleInlineQueryRegistry handleInlineQueryRegistry,
                                               HandleChosenInlineQueryRegistry handleChosenInlineQueryRegistry,
                                               HandlerAnnotationRegistryContainer handlerAnnotationRegistryContainer) {

        this.handleInlineQueryRegistry = handleInlineQueryRegistry;
        this.handleChosenInlineQueryRegistry = handleChosenInlineQueryRegistry;
        this.handlerAnnotationRegistryContainer = handlerAnnotationRegistryContainer;
    }

    @PostConstruct
    void registration() {
        handlerAnnotationRegistryContainer.addHandlerAnnotationRegistry(handleInlineQueryRegistry);
        handlerAnnotationRegistryContainer.addHandlerAnnotationRegistry(handleChosenInlineQueryRegistry);
    }
}
