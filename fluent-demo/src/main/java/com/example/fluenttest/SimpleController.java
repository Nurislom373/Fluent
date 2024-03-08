package com.example.fluenttest;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.ConditionOnState;
import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.expression.BotVariable;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.methods.HandleMessages;
import org.khasanof.enums.MatchType;
import org.khasanof.service.template.FluentTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see com.example.springfluenttest
 * @since 9/30/2023 11:26 PM
 */
@Slf4j
@UpdateController
public class SimpleController {

    private final FluentTemplate fluentTemplate;

    public SimpleController(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleMessages(value = {
        @HandleMessage(value = "/jeck1", match = MatchType.START_WITH),
        @HandleMessage(value = "/jeck2", match = MatchType.START_WITH),
    })
    @ConditionOnState({"START"})
    private void handleMessage(Update update) {
        fluentTemplate.sendText("Hi jecki");
    }

    @HandleMessage(value = "/username : {name:[a-z]}", match = MatchType.VAR_EXPRESSION)
    void startWithAbsHandler(Update update, @BotVariable("name") String username) {
        System.out.println("username = " + username);
        String text = update.getMessage().getText();
        log.info("Handle Start With 'abs' : {}", text);
        fluentTemplate.sendText("Start With 'abs' : " + text);
    }
}
