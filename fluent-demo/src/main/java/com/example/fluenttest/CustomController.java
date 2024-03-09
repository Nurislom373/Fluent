package com.example.fluenttest;

import com.example.fluenttest.custom.annotation.HandleSticker;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.HandleChosenInlineQuery;
import org.khasanof.annotation.HandleInlineQuery;
import org.khasanof.annotation.UpdateController;
import org.khasanof.enums.MatchType;
import org.khasanof.model.ChosenInlineQueryParam;
import org.khasanof.service.template.FluentTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see com.example.fluenttest
 * @since 2/21/2024 12:14 AM
 */
@Slf4j
@UpdateController
public class CustomController {

    private final FluentTemplate fluentTemplate;

    public CustomController(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleSticker("regular")
    public void handleLikeSticker() {
        fluentTemplate.sendText("Custom Annotation is worked!");
    }

    @HandleInlineQuery
    public void handleInlineQuery(Update update, String query) {
        System.out.println("Handle Inline Query : " + query);
        List<String> sites = List.of("Google", "Github", "Telegram", "Wikipedia");
        var results = new ArrayList<InlineQueryResult>();

        for (int i = 0; i < sites.size(); i++) {
            String site = sites.get(i);
            results.add(new InlineQueryResultArticle(String.valueOf(i), site, new InputTextMessageContent("Hello")));
        }

        fluentTemplate.sendAnswerInlineQuery(results);
    }

    @HandleChosenInlineQuery
    public void handleChosenInlineQuery(Update update, ChosenInlineQueryParam param) {
        log.info("chosen param : {}", param);
    }
}
