package org.khasanof.config;

import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.annotation.methods.HandleCallbacks;
import org.khasanof.enums.MatchType;
import org.khasanof.service.template.FluentTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;

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

    @HandleCallback({"RU", "UZ", "EN"})
    private void callBack() {
        System.out.println("Enter Sender !");
        String text = "<b> Choose bot language: </b>";
        fluentTemplate.sendText(text);
    }

    @HandleCallbacks({
            @HandleCallback(value = {"NEXT", "PREV"}),
            @HandleCallback(value = {"TOP", "BOTTOM"}),
            @HandleCallback(value = {"LST"}, match = MatchType.START_WITH)
    })
    private void multiCallback(Update update) {
        String text = "NPTB one handle \uD83D\uDE0E";
        fluentTemplate.sendText(text);
        fluentTemplate.sendAnswerCallbackQuery("Nurislom2", true);
    }
}
