package org.khasanof.factories.webhook;

import org.khasanof.config.FluentProperties;
import org.telegram.telegrambots.meta.generics.Webhook;

/**
 * @author Nurislom
 * @see org.khasanof.factories.webhook
 * @since 5/13/2024 9:49 PM
 */
public interface WebhookFactory {

    /**
     *
     * @param properties
     * @return
     */
    Webhook create(FluentProperties.Webhook properties);
}
