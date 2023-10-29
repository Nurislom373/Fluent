package org.khasanof.custom;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.custom
 * @since 10/28/2023 7:07 PM
 */
public record UpdateTask(Update update, Runnable runner) implements Runnable {

    @Override
    public void run() {
        runner.run();
    }

}
