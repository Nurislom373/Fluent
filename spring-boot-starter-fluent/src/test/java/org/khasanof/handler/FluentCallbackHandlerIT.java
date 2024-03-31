package org.khasanof.handler;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.khasanof.IntegrationTest;
import org.khasanof.UpdateFactoryFacade;
import org.khasanof.verifier.FluentVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.handler
 * @since 1/14/2024 1:08 AM
 */
@Disabled
@IntegrationTest
public class FluentCallbackHandlerIT {

    @Autowired
    private FluentVerifier fluentVerifier;

    @Autowired
    private UpdateFactoryFacade updateFactoryFacade;

    @ParameterizedTest
    @ValueSource(strings = {"EN", "RU", "UZ"})
    void firstTestShouldSuccess(String data) {
        Update update = updateFactoryFacade.createCallback(data);

        fluentVerifier.execute(update)
                .expectSendText("<b> Choose bot language: </b>");
    }

    @ParameterizedTest
    @ValueSource(strings = {"NEXT", "PREV", "TOP", "BOTTOM"})
    void secondTestSentCallbackShouldSuccess(String callbackData) {
        Update update = updateFactoryFacade.createCallback(callbackData);

        String text = "NPTB one handle \uD83D\uDE0E";
        fluentVerifier.execute(update)
                .expectSendText(text)
                .expectSendAnswerCallbackQuery();
    }

}
