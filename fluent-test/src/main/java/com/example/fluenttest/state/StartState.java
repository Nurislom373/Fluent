package com.example.fluenttest.state;

import com.example.fluenttest.SimpleState;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.state.State;
import org.khasanof.state.StateAction;
import org.khasanof.utils.UpdateUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * @author Nurislom
 * @see com.example.springfluenttest
 * @since 8/15/2023 9:30 PM
 */
@Slf4j
@Component
public class StartState implements StateAction<SimpleState> {

    @Override
    public void onReceive(Update update, AbsSender sender, State state) throws Exception {
        log.info("Hello World I'm Start State");
        sender.execute(new SendMessage(update.getMessage().getChatId().toString(), "Hello World I'm Start State"));
        state.nextState();
    }

    @Override
    public SimpleState state() {
        return SimpleState.START;
    }

    @Override
    public boolean updateHandlersProceed() {
        return StateAction.super.updateHandlersProceed();
    }
}
