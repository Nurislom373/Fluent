package org.khasanof;

import lombok.NoArgsConstructor;
import org.khasanof.config.FluentProperties;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 10:30
 * <br/>
 * Package: org.khasanof.main
 */
@NoArgsConstructor
public class FluentBot extends AbstractFluentBot {

    public FluentBot(UpdateHandlerManager handler, FluentProperties properties) {
        super(handler, properties.getBot());
    }

    @Override
    public String getBotUsername() {
        return bot.getUsername();
    }

    @Override
    public String getBotToken() {
        return bot.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasInlineQuery()) {
            sendAnswer(update);
            return;
        }
        handler.process(update);
    }

    private void sendAnswer(Update update) {
        List<String> sites = List.of("Google", "Github", "Telegram", "Wikipedia");
        InlineQuery inlineQuery = update.getInlineQuery();
        var results = new ArrayList<InlineQueryResult>();

        for (int i = 0; i < sites.size(); i++) {
            String site = sites.get(i);
            results.add(new InlineQueryResultArticle(String.valueOf(i), site, new InputTextMessageContent("Hello")));
        }

        AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery(inlineQuery.getId(), results);
        try {
            execute(answerInlineQuery);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
