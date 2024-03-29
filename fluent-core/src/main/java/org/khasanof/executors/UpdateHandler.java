package org.khasanof.executors;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 10/28/2023 9:41 PM
 */
public interface UpdateHandler {

    /**
     *
     * @param update
     */
    void execute(Update update);
}
