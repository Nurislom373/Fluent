package org.khasanof.factories.bot;

import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.meta.generics.Webhook;

/**
 * @author Nurislom
 * @see org.khasanof.factories.bot
 * @since 3/6/2024 11:05 PM
 */
public class WebhookTelegramBotApiFactory implements TelegramBotApiFactory {

    private final Webhook webhook;
    private final Class<? extends BotSession> botSessionClass;

    public WebhookTelegramBotApiFactory(Webhook webhook, Class<? extends BotSession> botSessionClass) {
        this.webhook = webhook;
        this.botSessionClass = botSessionClass;
    }

    @Override
    public TelegramBotsApi create() {
        try {
            return internalCreate();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private TelegramBotsApi internalCreate() throws TelegramApiException {
        return new TelegramBotsApi(botSessionClass, webhook);
    }
}
