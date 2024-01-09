package com.example.fluenttest;

import org.junit.jupiter.api.Test;
import org.khasanof.UpdateFactoryFacade;
import org.khasanof.verifier.FluentVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.api.objects.Update;

@SpringBootTest(classes = {FluentTestApplication.class})
class FluentTestApplicationTests {

    @Autowired
    private FluentVerifier fluentVerifier;

    @Autowired
    private UpdateFactoryFacade updateCreatorFacade;

    @Test
    void firstTestShouldSuccess() {
        Update update = updateCreatorFacade.createWithText("/fluent");

        fluentVerifier.execute(update)
                .expectSendMessage("I'm handle any messages")
                .expectSendMessage()
                .expectSendMessage("Handle Update With Expression");

    }

}
