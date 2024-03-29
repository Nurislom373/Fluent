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
    private UpdateFactoryFacade updateFactoryFacade;

    @Test
    void firstTestShouldSuccess() {
        Update update = updateFactoryFacade.createMessage("/fluent");

        fluentVerifier.execute(update)
                .expectSendText("I'm handle any messages")
                .expectSendText()
                .expectSendText("Handle Update With Expression");
    }

}
