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
 * @see com.example.springfluenttest
 * @since 8/15/2023 9:30 PM
 */
@Slf4j
@Component
public class StartState implements StateAction<SimpleState> {

    private final FluentTemplate fluentTemplate;

    public StartState(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @Override
    public void onReceive(Update update, State state) throws Exception {
        log.info("Hello World I'm Start State");
        fluentTemplate.sendText("Hello World I'm Start State");
        state.nextState();
    }

    @Override
    public SimpleState state() {
        return SimpleState.START;
    }
}
