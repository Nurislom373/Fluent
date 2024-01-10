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

    public Update createWithText(String text) {
        return updateAbstractFactory.updateMessageTextFactory()
                .create(text);
    }

    public Update createWithDocument(File file) {
        return updateAbstractFactory.updateMessageDocumentFactory()
                .create(file);
    }

    public Update createWithDocument(File file, String caption) {
        return updateAbstractFactory.updateMessageDocumentFactory()
                .create(file, caption);
    }

    public Update createWithDocument(String filename) {
        return updateAbstractFactory.updateMessageDocumentFactory()
                .create(filename);
    }

    public Update createWithDocument(String filename, String caption) {
        return updateAbstractFactory.updateMessageDocumentFactory()
                .create(filename, caption);
    }

    public Update createWithDocument(String filename, String caption, Long size) {
        return updateAbstractFactory.updateMessageDocumentFactory()
                .create(filename, caption, size);
    }

}
