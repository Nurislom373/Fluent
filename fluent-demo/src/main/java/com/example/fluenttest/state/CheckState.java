package com.example.fluenttest.state;

import com.example.fluenttest.SimpleState;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.service.template.FluentTemplate;
import org.khasanof.state.State;
import org.khasanof.state.StateAction;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see com.example.fluenttest.state
 * @since 6/8/2024 3:01 AM
 */
@Slf4j
@Component
public class CheckState implements StateAction<SimpleState> {

    private final FluentTemplate fluentTemplate;

    public CheckState(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @Override
    public void onReceive(Update update, State state) throws Exception {
        log.info("Hello World I'm Check State");
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("END")) {
            state.nextState();
        }
        fluentTemplate.sendText("Hello World I'm Check State");
    }

    @Override
    public SimpleState state() {
        return SimpleState.CHECK;
    }
}
