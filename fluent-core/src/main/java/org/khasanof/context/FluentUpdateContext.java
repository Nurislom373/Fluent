package org.khasanof.context;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 10/28/2023 6:52 PM
 */
public interface FluentUpdateContext {

    void addContext(String threadName, Update update);

    void removeContext(String threadName);

    Optional<FluentUpdate> getFluentUpdate(String threadName);

}
