package org.khasanof.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.khasanof.IntegrationTest;
import org.khasanof.UpdateFactoryFacade;
import org.khasanof.verifier.FluentVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/14/2024 12:47 AM
 */
@IntegrationTest
public class FluentMessageHandleIT {

    @Autowired
    private FluentVerifier fluentVerifier;

    @Autowired
    private UpdateFactoryFacade updateFactoryFacade;

    @Test
    void firstTestShouldSuccess() {
        Update update = updateFactoryFacade.createMessage("/fluent");

        fluentVerifier.execute(update)
                .expectSendText("I'm handle any messages")
                .expectSendText("Handle Update With Expression");
    }

    @Test
    void secondTestShouldSuccess() {
        Update update = updateFactoryFacade.createMessage("/start");

        fluentVerifier.execute(update)
                .expectSendText("I'm handle any messages")
                .expectSendText("/start");
    }

    @Test
    void thirdTestShouldSuccess() {
        Update update = updateFactoryFacade.createMessage("/username : abdulloh");

        fluentVerifier.execute(update)
                .expectSendText("I'm handle any messages")
                .expectSendText("name : abdulloh");
    }

    @ParameterizedTest
    @ValueSource(strings = {"/jeck1", "/jeck2"})
    void fourthTestShouldSuccess(String command) {
        Update update = updateFactoryFacade.createMessage(command);

        fluentVerifier.execute(update)
                .expectSendText("I'm handle any messages")
                .expectSendText("Hi Jecki😎");
    }

}
