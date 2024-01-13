package org.khasanof.config;

import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.custom.attributes.UpdateAttributes;
import org.khasanof.enums.HandleType;
import org.khasanof.enums.MatchScope;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Nurislom
 * @see org.khasanof.handler
 * @since 1/14/2024 12:44 AM
 */
@UpdateController
public class TestMessageHandler {

    @HandleAny(type = HandleType.MESSAGE)
    private void handleAnyMessages(Update update, AbsSender sender) throws TelegramApiException {
        UpdateAttributes attributes = FluentContextHolder.getAttributes();
        attributes.setAttribute("foo", "bar");
        String text = "I'm handle any messages";
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleMessage(value = "/start", scope = MatchScope.START_WITH)
    public void fluent(Update update, AbsSender sender) throws TelegramApiException {
        UpdateAttributes attributes = FluentContextHolder.getAttributes();
        String text = update.getMessage().getText();
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleMessage(value = "START_WITH('/fluent', value)", scope = MatchScope.EXPRESSION)
    public void handleStartWithFluent(Update update, AbsSender sender) throws TelegramApiException {
        String text = "Handle Update With Expression";
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

}
