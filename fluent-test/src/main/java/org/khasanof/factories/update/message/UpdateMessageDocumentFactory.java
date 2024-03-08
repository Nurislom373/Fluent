package org.khasanof.factories.update.message;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

/**
 * @author Nurislom
 * @see org.khasanof.factories.update
 * @since 1/10/2024 9:39 PM
 */
public interface UpdateMessageDocumentFactory {

    Update create(File file);

    Update create(File file, String caption);

    Update create(String filename);

    Update create(String filename, String caption);

    Update create(String filename, String caption, Long size);

}
