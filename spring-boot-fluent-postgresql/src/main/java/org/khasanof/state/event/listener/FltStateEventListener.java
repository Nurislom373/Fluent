package org.khasanof.state.event.listener;

import org.khasanof.service.FltStateEventService;
import org.khasanof.state.event.FltStateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.state.event.listener
 * @since 3/2/2024 12:14 PM
 */
@Component
public class FltStateEventListener {

    private final FltStateEventService fltStateEventService;

    public FltStateEventListener(FltStateEventService fltStateEventService) {
        this.fltStateEventService = fltStateEventService;
    }

    @EventListener
    public void handleFltStateEvent(FltStateEvent event) {
        fltStateEventService.updateState(event);
    }
}
