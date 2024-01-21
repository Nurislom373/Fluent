package org.khasanof.config;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.expression.BotVariable;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.methods.HandleMessages;
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
@Slf4j
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

    @HandleMessages(values = {
            @HandleMessage(value = "/jeck1", scope = MatchScope.START_WITH),
            @HandleMessage(value = "/jeck2", scope = MatchScope.START_WITH),
    })
    private void handleMessage(Update update, AbsSender sender) throws TelegramApiException {
        String chatId = update.getMessage().getChatId().toString();
        log.info("Jecki is here!");
        SendMessage message = new SendMessage(chatId, "Hi Jecki😎");
        sender.execute(message);
    }

    @HandleMessage(value = "/username : {name:[a-z]}", scope = MatchScope.VAR_EXPRESSION)
    void startWithAbsHandler(Update update, AbsSender sender, @BotVariable("name") String username) throws TelegramApiException {
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        log.info("Handle Start With 'abs' : {}", text);
        SendMessage message = new SendMessage(chatId, "name : " + username);
        sender.execute(message);
    }

}
