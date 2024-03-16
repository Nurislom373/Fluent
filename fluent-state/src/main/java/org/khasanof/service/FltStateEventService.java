package org.khasanof.service;

import org.khasanof.state.event.FltStateEvent;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 3/17/2024 12:03 AM
 */
public interface FltStateEventService {

    /**
     *
     * @param event
     */
    void updateState(FltStateEvent event);
}
