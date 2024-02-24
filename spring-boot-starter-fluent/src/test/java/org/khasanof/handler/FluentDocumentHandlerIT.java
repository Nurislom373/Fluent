package org.khasanof.handler;

import org.junit.jupiter.api.Test;
import org.khasanof.IntegrationTest;
import org.khasanof.UpdateFactoryFacade;
import org.khasanof.verifier.FluentVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.handler
 * @since 2/24/2024 11:08 PM
 */
@IntegrationTest
public class FluentDocumentHandlerIT {

    @Autowired
    private FluentVerifier fluentVerifier;

    @Autowired
    private UpdateFactoryFacade updateFactoryFacade;

    @Test
    void firstTestShouldSuccess() {
        Update update = updateFactoryFacade.createDocument("584675946754654.png");

        fluentVerifier.execute(update)
                .expectSendText("I'm handle any messages")
                .expectSendText("I Handle 1 File");
    }

    @Test
    void secondTestShouldSuccess() {
        Update update = updateFactoryFacade.createDocument("584675946754654.jpeg");

        fluentVerifier.execute(update)
                .expectSendText("I'm handle any messages")
                .expectSendText("I Handle 1 File");
    }

    @Test
    void thirdTestShouldSuccess() {
        Update update = updateFactoryFacade.createDocument("food_polov.mp4");

        fluentVerifier.execute(update)
                .expectSendText("I'm handle any messages")
                .expectSendText("I Handle file name start with food");
    }

    @Test
    void fourthTestShouldSuccess() {
        Update update = updateFactoryFacade.createDocument("foods89345643543543.mp3");

        fluentVerifier.execute(update)
                .expectSendText("I'm handle any messages")
                .expectSendText("I Handle file name start with food");
    }

    @Test
    void fifthTestShouldSuccess() {
        Update update = updateFactoryFacade.createDocument("346534765743.mp3");

        fluentVerifier.execute(update)
                .expectSendText("I'm handle any messages");
    }

    @Test
    void sixthTestShouldSuccess() {
        Update update = updateFactoryFacade.createDocument("96756454.mp3");

        fluentVerifier.execute(update)
                .expectSendTextCount(1);
    }
}
