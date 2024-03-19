package com.example.fluenttest;

import com.example.fluenttest.custom.condition.CustomCondition;
import org.khasanof.annotation.ConditionOnExpression;
import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.expression.BotVariable;
import org.khasanof.annotation.methods.*;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.custom.attributes.Attributes;
import org.khasanof.enums.HandleType;
import org.khasanof.enums.MatchType;
import org.khasanof.enums.scopes.DocumentScope;
import org.khasanof.enums.scopes.PhotoScope;
import org.khasanof.enums.scopes.VideoScope;
import org.khasanof.service.template.FluentTemplate;
import org.khasanof.utils.keyboards.inline.InlineKeyboardMarkupBuilder;
import org.khasanof.utils.keyboards.reply.ReplyKeyboardMarkupBuilder;
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
    @ConditionOnExpression("#{conditionBean.exist()}")
    public void templateExample(Update update) {
        fluentTemplate.sendText("Hello World");
    }

    @HandleMessage(value = "message:", match = MatchType.STARTS_WITH)
    public void startWithExampleHandler() {
        fluentTemplate.sendText("I handle requests start with 'message:'");
    }

    @CustomCondition
    @HandleAny(type = {HandleType.MESSAGE, HandleType.AUDIO})
    private void handleAnyMessagesWithUpdate(Update update) {
        Attributes attributes = FluentContextHolder.getCurrentAttributes();
        attributes.setAttribute("foo", "bar");
        System.out.println(Objects.nonNull(update));
        String text = "I'm handle any messages with update";
        fluentTemplate.sendText(text);
    }

    @HandleAny(type = HandleType.MESSAGE)
    private void handleAnyMessages() {
        Attributes attributes = FluentContextHolder.getCurrentAttributes();
        attributes.setAttribute("foo", "bar");
        String text = "I'm handle any updates";
        fluentTemplate.sendText(text);
    }

    @HandleMessage(value = "/send_username {name:[a-z]}", match = MatchType.VAR_EXPRESSION)
    public void handleVarExpression(@BotVariable("name") String name) {
        String text = "Hello ".concat(name).concat("!");
        fluentTemplate.sendText(text);
    }

    @HandleVideo(value = "caption:", match = MatchType.STARTS_WITH, property = VideoScope.CAPTION)
    public void handleVideoCaption() {
        fluentTemplate.sendText("Handle start with 'caption:' text video");
    }

    @HandleDocument(value = "caption:", match = MatchType.STARTS_WITH, property = DocumentScope.CAPTION)
    public void handleDocumentCaption() {
        fluentTemplate.sendText("Handle start with 'caption:' text document");
    }

    @HandleMessage(value = "/button")
    public void handleButtonCommand() {
        InlineKeyboardMarkupBuilder builder = new InlineKeyboardMarkupBuilder();

        builder.addButton("First")
                .callbackData("First");
        builder.addButton("Second")
                .callbackData("Second");

        builder.addRow();

        builder.addButton("Third")
                .callbackData("Third");
        builder.addButton("Fourth")
                .callbackData("Fourth");

        fluentTemplate.sendText("This is inline button", builder.build());
    }

    @HandleMessage(value = "/reply")
    public void handleReplyCommand() {
        ReplyKeyboardMarkupBuilder builder = new ReplyKeyboardMarkupBuilder();
        builder.oneTimeKeyboard(true);

        builder.addButton("First");
        builder.addButton("Second");

        builder.addRow();

        builder.addButton("Third");
        builder.addButton("Fourth");

        fluentTemplate.sendText("This is reply button", builder.build());
    }

    @ConditionOnExpression("1 == 1")
    @ConditionOnExpression("2 == 2")
    @HandleMessage(value = "/start", match = MatchType.STARTS_WITH)
    public void fluent(Update update) {
        Attributes attributes = FluentContextHolder.getCurrentAttributes();
        String text = update.getMessage().getText();
        fluentTemplate.sendText(text);
    }

    @HandlePhoto(value = "start: ", match = MatchType.STARTS_WITH, property = PhotoScope.CAPTION)
    public void handleStartCaptionPhoto() {
        fluentTemplate.sendText("Hi I handle photo");
    }

    @HandleCallback({"EN", "RU", "UZ"})
    private void handleCallback() {
        String text = "Callback handler!";
        fluentTemplate.sendText(text);
    }

    @HandleMessage(value = "#value.startsWith('/fluent')", match = MatchType.EXPRESSION)
    public void handleStartWithFluent() {
        String text = "Handle Update With Expression";
        fluentTemplate.sendText(text);
    }

    @HandleMessage(value = "#value.startsWith('a') && #value.endsWith('z')", match = MatchType.EXPRESSION)
    public void handleStartWithA() {
        fluentTemplate.sendText("...");

        InputStream inputStream = getClass().getResourceAsStream("/darelian-instasamka-khlopai-phonk-house-remix-tekst.m4a");
        fluentTemplate.sendAudio(inputStream, "jeck.m4a", "Hello Sardorbro");
    }

    @HandleUnknown
    public void handleUnknown(Update update) {
        if (update.hasMessage()) {
            fluentTemplate.sendText("yuborilgan command topilmadi!!!");
        }
    }
}
