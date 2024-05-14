package org.khasanof.manager;

import org.khasanof.config.FluentProperties;
import org.khasanof.enums.BotType;
import org.khasanof.factories.bot.DefaultTelegramBotApiFactory;
import org.khasanof.factories.bot.TelegramBotApiFactory;
import org.khasanof.factories.bot.WebhookTelegramBotApiFactory;
import org.khasanof.factories.webhook.WebhookFactory;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.manager
 * @since 5/13/2024 9:46 PM
 */
public class TelegramBotApiFactoryManager {

    /**
     *
     * @param properties
     * @param webhookFactory
     * @return
     */
    public TelegramBotApiFactory getFactory(FluentProperties properties, WebhookFactory webhookFactory) {
        if (isLongPolling(properties)) {
            return new DefaultTelegramBotApiFactory(DefaultBotSession.class);
        }
        return new WebhookTelegramBotApiFactory(webhookFactory.create(properties.getWebhook()), DefaultBotSession.class);
    }

    private boolean isLongPolling(FluentProperties properties) {
        return Objects.equals(properties.getBot().getType(), BotType.LONG_POLLING);
    }
}
