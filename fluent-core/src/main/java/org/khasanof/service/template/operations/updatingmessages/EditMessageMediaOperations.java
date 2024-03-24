package org.khasanof.service.template.operations.updatingmessages;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations.updatingmessages
 * @since 3/24/2024 12:45 PM
 */
public interface EditMessageMediaOperations {

    // file

    Serializable editMessageMedia(Integer messageId, File media);

    Serializable editMessageMedia(Integer messageId, File media, Long chatId);

    Serializable editMessageMedia(String inlineMessageId, File media);

    Serializable editMessageMedia(String inlineMessageId, File media, Long chatId);

    Serializable editMessageMedia(Integer messageId, File media, ReplyKeyboard replyKeyboard);

    Serializable editMessageMedia(Integer messageId, File media, Long chatId, ReplyKeyboard replyKeyboard);

    Serializable editMessageMedia(String inlineMessageId, File media, ReplyKeyboard replyKeyboard);

    Serializable editMessageMedia(String inlineMessageId, File media, Long chatId, ReplyKeyboard replyKeyboard);

    // input stream

    Serializable editMessageMedia(Integer messageId, InputStream media, String filename);

    Serializable editMessageMedia(Integer messageId, InputStream media, String filename, Long chatId);

    Serializable editMessageMedia(String inlineMessageId, InputStream media, String filename);

    Serializable editMessageMedia(String inlineMessageId, InputStream media, String filename, Long chatId);

    Serializable editMessageMedia(Integer messageId, InputStream media, String filename, ReplyKeyboard replyKeyboard);

    Serializable editMessageMedia(Integer messageId, InputStream media, String filename, Long chatId, ReplyKeyboard replyKeyboard);

    Serializable editMessageMedia(String inlineMessageId, InputStream media, String filename, ReplyKeyboard replyKeyboard);

    Serializable editMessageMedia(String inlineMessageId, InputStream media, String filename, Long chatId, ReplyKeyboard replyKeyboard);

    //

    Serializable editMessageMedia(EditMessageMedia editMessageMedia);
}
