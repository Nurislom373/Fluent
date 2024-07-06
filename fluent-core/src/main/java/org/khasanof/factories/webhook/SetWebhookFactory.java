package org.khasanof.factories.webhook;

import org.khasanof.config.FluentProperties;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

/**
 * @author Nurislom
 * @see org.khasanof.factories.webhook
 * @since 5/13/2024 10:07 PM
 */
public interface SetWebhookFactory {

    /**
     *
     * @param webhook
     * @return
     */
    SetWebhook create(FluentProperties.Webhook webhook);
}
