package org.khasanof;

import org.khasanof.factories.UpdateAbstractFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:31 PM
 */
public class UpdateFactoryFacade {

    private final UpdateAbstractFactory updateAbstractFactory;

    public UpdateFactoryFacade(UpdateAbstractFactory updateAbstractFactory) {
        this.updateAbstractFactory = updateAbstractFactory;
    }

    public Update createMessage(String text) {
        return updateAbstractFactory.updateMessageTextFactory()
                .create(text);
    }

    public Update createCallback(String data) {
        return updateAbstractFactory.updateCallbackQueryFactory()
                .create(data);
    }

    public Update createDocument(File file) {
        return updateAbstractFactory.updateMessageDocumentFactory()
                .create(file);
    }

    public Update createDocument(File file, String caption) {
        return updateAbstractFactory.updateMessageDocumentFactory()
                .create(file, caption);
    }

    public Update createDocument(String filename) {
        return updateAbstractFactory.updateMessageDocumentFactory()
                .create(filename);
    }

    public Update createDocument(String filename, String caption) {
        return updateAbstractFactory.updateMessageDocumentFactory()
                .create(filename, caption);
    }

    public Update createDocument(String filename, String caption, Long size) {
        return updateAbstractFactory.updateMessageDocumentFactory()
                .create(filename, caption, size);
    }

}
