package org.khasanof.state;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 8/15/2023 9:43 PM
 */
public interface Action {

    void onReceive(Update update, State state) throws Exception;

    boolean updateHandlersProceed();

}
