package org.khasanof.config;

import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.service.template.FluentTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 1/14/2024 1:05 AM
 */
@UpdateController
public class TestCallbackHandler {

    private final FluentTemplate fluentTemplate;

    public TestCallbackHandler(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleCallback(value = {"RU", "UZ", "EN"})
    private void callBack() {
        System.out.println("Enter Sender !");
        String text = "<b> Choose bot language: </b>";
        fluentTemplate.sendText(text);
    }

}
