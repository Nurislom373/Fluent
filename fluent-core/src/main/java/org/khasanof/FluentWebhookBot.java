package org.khasanof;

import lombok.NoArgsConstructor;
import org.khasanof.config.FluentProperties;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodBoolean;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/13/2024 9:09 PM
 */
@NoArgsConstructor
public class FluentWebhookBot extends TelegramWebhookBot {

    protected UpdateHandlerManager handler;
    protected FluentProperties fluentProperties;

    public FluentWebhookBot(UpdateHandlerManager handler, FluentProperties fluentProperties, SetWebhook webhook) {
        super(fluentProperties.getBot().getToken());
        this.handler = handler;
        this.fluentProperties = fluentProperties;
        trySetWebhook(webhook);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        handler.process(update);
        return new BotApiMethodBoolean() {
            @Override
            public String getMethod() {
                return "ok";
            }
        };
    }

    @Override
    public String getBotPath() {
        return fluentProperties.getWebhook()
                .getUrl();
    }

    @Override
    public String getBotUsername() {
        return fluentProperties.getBot()
                .getUsername();
    }

    private void trySetWebhook(SetWebhook webhook) {
        try {
            setWebhook(webhook);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
