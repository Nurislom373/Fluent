package com.example.fluenttest;

import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.methods.HandleMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Nurislom
 * @see com.example.fluenttest
 * @since 10/12/2023 9:40 PM
 */
@UpdateController
public class TestController {

    @HandleMessage("/start")
    void test(Update update, AbsSender sender) throws TelegramApiException {
        String text = update.getMessage().getText();
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

}
