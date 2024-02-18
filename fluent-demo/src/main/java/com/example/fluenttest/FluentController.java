package com.example.fluenttest;

import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.expression.BotVariable;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.methods.HandlePhoto;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.custom.attributes.UpdateAttributes;
import org.khasanof.enums.HandleType;
import org.khasanof.enums.MatchScope;
import org.khasanof.enums.scopes.PhotoScope;
import org.khasanof.service.template.FluentTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.InputStream;
import java.util.Objects;

/**
 * @author Nurislom
 * @see com.example.fluenttest
 * @since 10/12/2023 9:40 PM
 */
@UpdateController
public class FluentController {

    private final FluentTemplate fluentTemplate;

    public FluentController(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleMessage("/template")
    public void templateExample(Update update) {
        fluentTemplate.sendText("Hello World");
    }

    @HandleAny(type = {HandleType.MESSAGE, HandleType.AUDIO})
    private void handleAnyMessagesWithUpdate(Update update) {
        UpdateAttributes attributes = FluentContextHolder.getAttributes();
        attributes.setAttribute("foo", "bar");
        System.out.println(Objects.nonNull(update));
        String text = "I'm handle any messages with update";
        fluentTemplate.sendText(text);
    }

    @HandleAny(type = HandleType.ALL)
    private void handleAnyMessages() {
        UpdateAttributes attributes = FluentContextHolder.getAttributes();
        attributes.setAttribute("foo", "bar");
        String text = "I'm handle any updates";
        fluentTemplate.sendText(text);
    }

    @HandleMessage(value = "/send_username {name:[a-z]}", scope = MatchScope.VAR_EXPRESSION)
    public void handleVarExpression(@BotVariable("name") String name) {
        String text = "Hello ".concat(name).concat("!");
        fluentTemplate.sendText(text);
    }


    @HandleMessage(value = "/start", scope = MatchScope.START_WITH)
    public void fluent(Update update) {
        UpdateAttributes attributes = FluentContextHolder.getAttributes();
        String text = update.getMessage().getText();
        fluentTemplate.sendText(text);
    }

    @HandlePhoto(value = "start: ", match = MatchScope.START_WITH, scope = PhotoScope.CAPTION)
    public void handleStartCaptionPhoto(Update update) {
        fluentTemplate.sendText("Hi I handle photo");
    }

    @HandleCallback({"EN", "RU", "UZ"})
    private void handleCallback(Update update) {
        String text = "Callback handler!";
        fluentTemplate.sendText(text);
    }

    @HandleMessage(value = "START_WITH('/fluent', value)", scope = MatchScope.EXPRESSION)
    public void handleStartWithFluent(Update update) {
        String text = "Handle Update With Expression";
        fluentTemplate.sendText(text);
    }

    @HandleMessage(value = "START_WITH('a', value) && END_WITH('z', value)", scope = MatchScope.EXPRESSION)
    public void handleStartWithA(Update update) {
        fluentTemplate.sendText("...");

        InputStream inputStream = getClass().getResourceAsStream("/darelian-instasamka-khlopai-phonk-house-remix-tekst.m4a");
        fluentTemplate.sendAudio(inputStream, "jeck.m4a", "Hello Sardorbro");
    }
}
