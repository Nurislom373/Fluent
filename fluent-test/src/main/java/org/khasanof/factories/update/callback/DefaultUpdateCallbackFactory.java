package org.khasanof.factories.update.callback;

import org.khasanof.factories.update.AbstractUpdateFactory;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.factories.update.callback
 * @since 1/13/2024 10:52 PM
 */
public class DefaultUpdateCallbackFactory extends AbstractUpdateFactory implements UpdateCallbackDataFactory {

    @Override
    public Update create(String data) {
        return createCallbackQuery(() -> this.createCallbackQuery(data));
    }

    private CallbackQuery createCallbackQuery(String data) {
        CallbackQuery defaultCallback = createDefaultCallback();
        defaultCallback.setData(data);
        return defaultCallback;
    }

}
