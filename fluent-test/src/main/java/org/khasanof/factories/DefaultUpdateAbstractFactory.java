package org.khasanof.factories;

import org.khasanof.factories.update.DefaultUpdateMessageDocumentFactory;
import org.khasanof.factories.update.DefaultUpdateMessageTextFactory;
import org.khasanof.factories.update.UpdateMessageDocumentFactory;
import org.khasanof.factories.update.UpdateMessageTextFactory;

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
}
