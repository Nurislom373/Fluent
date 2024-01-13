package org.khasanof.factories.update.callback;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.factories.update.callback
 * @since 1/10/2024 10:46 PM
 */
public interface UpdateCallbackDataFactory {

    Update create(String data);

}
