package org.khasanof.factories;

import org.khasanof.factories.update.callback.UpdateCallbackDataFactory;
import org.khasanof.factories.update.message.UpdateMessageDocumentFactory;
import org.khasanof.factories.update.message.UpdateMessageTextFactory;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 1/9/2024 9:20 PM
 */
public interface UpdateAbstractFactory {

    UpdateMessageTextFactory updateMessageTextFactory();

    UpdateMessageDocumentFactory updateMessageDocumentFactory();

    UpdateCallbackDataFactory updateCallbackQueryFactory();

}
