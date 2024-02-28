package org.khasanof.config;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.expression.BotVariable;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.methods.HandleMessages;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.custom.attributes.Attributes;
import org.khasanof.enums.HandleType;
import org.khasanof.enums.MatchType;
import org.khasanof.service.template.FluentTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.handler
 * @since 1/14/2024 12:44 AM
 */
@Slf4j
@UpdateController
public class TestMessageHandler {

    private final FluentTemplate fluentTemplate;

    public TestMessageHandler(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleAny(type = HandleType.MESSAGE)
    private void handleAnyMessages() {
        Attributes attributes = FluentContextHolder.getAttributes();
        attributes.setAttribute("foo", "bar");
        String text = "I'm handle any messages";
        fluentTemplate.sendText(text);
    }

    @HandleMessage(value = "/start", match = MatchType.START_WITH)
    public void fluent(Update update) {
        Attributes attributes = FluentContextHolder.getAttributes();
        String text = update.getMessage().getText();
        fluentTemplate.sendText(text);
    }

    @HandleMessage(value = "#value.startsWith('/fluent')", match = MatchType.EXPRESSION)
    public void handleStartWithFluent() {
        String text = "Handle Update With Expression";
        fluentTemplate.sendText(text);
    }

    @HandleMessages(value = {
            @HandleMessage(value = "/jeck1", match = MatchType.START_WITH),
            @HandleMessage(value = "/jeck2", match = MatchType.START_WITH),
    })
    private void handleMessage() {
        log.info("Jecki is here!");
        fluentTemplate.sendText("Hi Jecki😎");
    }

    @HandleMessage(value = "/username : {name:[a-z]}", match = MatchType.VAR_EXPRESSION)
    void startWithAbsHandler(Update update, @BotVariable("name") String username) {
        String text = update.getMessage().getText();
        log.info("Handle Start With 'abs' : {}", text);
        fluentTemplate.sendText("name : " + username);
    }

}
