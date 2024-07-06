package org.khasanof.factories.webhook;

import org.khasanof.config.FluentProperties;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.Webhook;
import org.telegram.telegrambots.updatesreceivers.DefaultWebhook;

/**
 * @author Nurislom
 * @see org.khasanof.factories.webhook
 * @since 5/13/2024 9:49 PM
 */
public class DefaultWebhookFactory implements WebhookFactory {

    @Override
    public Webhook create(FluentProperties.Webhook properties) {
        DefaultWebhook webhook = new DefaultWebhook();
        tryCreate(properties, webhook);
        return webhook;
    }

    private void tryCreate(FluentProperties.Webhook properties, DefaultWebhook webhook) {
        try {
            webhook.setInternalUrl(properties.getUrl());
            webhook.setKeyStore(properties.getKeystoreServerFile(), properties.getKeystoreServerPwd());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
