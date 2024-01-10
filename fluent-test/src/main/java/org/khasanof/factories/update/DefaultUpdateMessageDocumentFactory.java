package org.khasanof.factories.update;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

/**
 * @author Nurislom
 * @see org.khasanof.factories.update
 * @since 1/10/2024 9:39 PM
 */
public class DefaultUpdateMessageDocumentFactory extends AbstractUpdateFactory implements UpdateMessageDocumentFactory {

    @Override
    public Update create(File file) {
        return create(() -> this.createDocument(file));
    }

    @Override
    public Update create(File file, String caption) {
        return create(() -> this.createDocument(file, caption));
    }

    @Override
    public Update create(String filename) {
        return create(() -> this.createDocument(filename));
    }

    @Override
    public Update create(String filename, String caption) {
        return create(() -> this.createDocument(filename, caption));
    }

    @Override
    public Update create(String filename, String caption, Long size) {
        return create(() -> this.createDocument(filename, caption, size));
    }

    private Message createDocument(File file) {
        return createDocument(file, null);
    }

    private Message createDocument(String filename) {
        return createDocument(filename, null);
    }

    private Message createDocument(String filename, String caption) {
        return createDocument(filename, caption, RandomUtils.nextLong());
    }

    private Message createDocument(String filename, String caption, Long size) {
        Document document = new Document();
        document.setFileId(RandomStringUtils.random(10, true, true));
        document.setFileName(filename);
        document.setFileSize(size);
        document.setFileUniqueId(RandomStringUtils.random(10, true, true));
        document.setMimeType(StringUtils.getFilenameExtension(filename));

        return createMessage(caption, document);
    }

    private Message createDocument(File file, String caption) {
        Document document = new Document();
        document.setFileId(RandomStringUtils.random(10, true, true));
        document.setFileName(file.getName());
        document.setFileSize(file.length());
        document.setFileUniqueId(RandomStringUtils.random(10, true, true));
        document.setMimeType(StringUtils.getFilenameExtension(file.getName()));

        return createMessage(caption, document);
    }

    @NotNull
    private Message createMessage(String caption, Document document) {
        Message message = createDefaultMessage();
        message.setCaption(caption);
        message.setDocument(document);
        return message;
    }
}
