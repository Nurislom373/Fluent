package com.example.fluenttest;

import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.expression.BotVariable;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.methods.chat.HandleMyChatMember;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.context.FluentUpdateContext;
import org.khasanof.custom.attributes.UpdateAttributes;
import org.khasanof.enums.HandleType;
import org.khasanof.enums.MatchScope;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;

/**
 * @author Nurislom
 * @see com.example.fluenttest
 * @since 10/12/2023 9:40 PM
 */
@UpdateController
public class FluentController {

    @HandleMessage(value = "/start", scope = MatchScope.START_WITH)
    public void fluent(Update update, AbsSender sender) throws TelegramApiException {
        UpdateAttributes attributes = FluentContextHolder.getAttributes();
        String text = update.getMessage().getText();
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleMyChatMember
    public void checkMyChatMember(Update update, AbsSender sender) {
        ChatMemberUpdated myChatMember = update.getMyChatMember();
        System.out.println("myChatMember = " + myChatMember);
    }

    @HandleAny(type = HandleType.MESSAGE)
    private void handleAnyMessages(Update update, AbsSender sender) throws TelegramApiException {
        UpdateAttributes attributes = FluentContextHolder.getAttributes();
        attributes.setAttribute("foo", "bar");
        String text = "I'm handle any messages";
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleCallback(values = {"EN", "RU", "UZ"})
    private void handleCallback(Update update, AbsSender sender) throws TelegramApiException {
        String text = "Callback handler!";
        SendMessage message = new SendMessage(update.getCallbackQuery().getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleMessage(value = "START_WITH('/fluent', value)", scope = MatchScope.EXPRESSION)
    public void handleStartWithFluent(Update update, AbsSender sender) throws TelegramApiException {
        String text = "Handle Update With Expression";
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleMessage(value = "START_WITH('a', value) && END_WITH('z', value)", scope = MatchScope.EXPRESSION)
    public void handleStartWithA(Update update, AbsSender sender) throws TelegramApiException {
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), "...");
        message.setReplyToMessageId(update.getMessage().getMessageId());
        sender.execute(message);

        InputStream inputStream = getClass().getResourceAsStream("/darelian-instasamka-khlopai-phonk-house-remix-tekst.m4a");

        SendAudio sendAudio = new SendAudio();
        sendAudio.setCaption("Hello Sardorbro");
        sendAudio.setChatId(update.getMessage().getChatId());
        sendAudio.setTitle("jeck.m4a");
        sendAudio.setAudio(new InputFile(inputStream, "jeck.m4a"));
        sender.execute(sendAudio);
    }

    @HandleMessage(value = "/send_username {name:[a-z]}", scope = MatchScope.VAR_EXPRESSION)
    public void handleVarExpression(Update update, AbsSender sender, @BotVariable("name") String name) throws TelegramApiException {
        String text = "Hello ".concat(name).concat("!");
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.executeAsync(message);
    }

}
