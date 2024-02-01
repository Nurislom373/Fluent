package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations
 * @since 1/24/2024 11:26 PM
 */
public interface SendDocumentOperations {

    Message sendDocument(File file);

    Message sendDocument(File file, Long chatId);

    Message sendDocument(File file, String caption);

    Message sendDocument(File file, Integer replyMessageId);

    Message sendDocument(File file, ReplyKeyboard replyKeyboard);

    Message sendDocument(File file, Long chatId, String caption);

    Message sendDocument(File file, Long chatId, Integer replyMessageId);

    Message sendDocument(File file, Long chatId, ReplyKeyboard replyKeyboard);

    Message sendDocument(File file, Long chatId, ReplyKeyboard replyKeyboard, String caption);

    Message sendDocument(InputStream inputStream, String filename);

    Message sendDocument(InputStream inputStream, String filename, Long chatId);

    Message sendDocument(InputStream inputStream, String filename, String caption);

    Message sendDocument(InputStream inputStream, String filename, Integer replyMessageId);

    Message sendDocument(InputStream inputStream, String filename, ReplyKeyboard replyKeyboard);

    Message sendDocument(InputStream inputStream, String filename, Long chatId, String caption);

    Message sendDocument(InputStream inputStream, String filename, Long chatId, Integer replyMessageId);

    Message sendDocument(InputStream inputStream, String filename, Long chatId, ReplyKeyboard replyKeyboard);

    Message sendDocument(InputStream inputStream, String filename, Long chatId, ReplyKeyboard replyKeyboard, String caption);

    Message sendDocument(InputStream inputStream, String filename, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String caption);

}
