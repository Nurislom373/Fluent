package org.khasanof.factories;

import org.khasanof.factories.update.callback.DefaultUpdateCallbackFactory;
import org.khasanof.factories.update.callback.UpdateCallbackDataFactory;
import org.khasanof.factories.update.message.DefaultUpdateMessageDocumentFactory;
import org.khasanof.factories.update.message.DefaultUpdateMessageTextFactory;
import org.khasanof.factories.update.message.UpdateMessageDocumentFactory;
import org.khasanof.factories.update.message.UpdateMessageTextFactory;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 1/9/2024 9:29 PM
 */
public class DefaultUpdateAbstractFactory implements UpdateAbstractFactory {

    @Override
    public UpdateMessageTextFactory updateMessageTextFactory() {
        return new DefaultUpdateMessageTextFactory();
    }

    @Override
    public UpdateMessageDocumentFactory updateMessageDocumentFactory() {
        return new DefaultUpdateMessageDocumentFactory();
    }

    @Override
    public UpdateCallbackDataFactory updateCallbackQueryFactory() {
        return new DefaultUpdateCallbackFactory();
    }
}
