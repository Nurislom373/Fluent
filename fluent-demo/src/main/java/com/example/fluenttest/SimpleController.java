package com.example.fluenttest;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.expression.BotVariable;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.methods.HandleMessages;
import org.khasanof.enums.MatchType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Nurislom
 * @see com.example.springfluenttest
 * @since 9/30/2023 11:26 PM
 */
@Slf4j
//@UpdateController
public class SimpleController {

    @HandleMessages(value = {
        @HandleMessage(value = "/jeck1", match = MatchType.START_WITH),
        @HandleMessage(value = "/jeck2", match = MatchType.START_WITH),
    })
    private void handleMessage(Update update, AbsSender sender) throws TelegramApiException {
        String chatId = update.getMessage().getChatId().toString();
        SendMessage message = new SendMessage(chatId, "Hi Jecki😎");
        sender.execute(message);
    }

    @HandleMessage(value = "/username : {name:[a-z]}", match = MatchType.VAR_EXPRESSION)
    void startWithAbsHandler(Update update, AbsSender sender, @BotVariable("name") String username) throws TelegramApiException {
        System.out.println("username = " + username);
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        log.info("Handle Start With 'abs' : {}", text);
        SendMessage message = new SendMessage(chatId, "Start With 'abs' : " + text);
        sender.execute(message);
    }

}
