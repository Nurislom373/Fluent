package org.khasanof;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 10:38
 * <br/>
 * Package: org.khasanof.main.inferaces
 */
public interface UpdateHandlerManager {

    /**
     *
     * @param update
     */
    void process(Update update);
}
