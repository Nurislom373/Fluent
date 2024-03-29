package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
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

    Message sendDocument(File file, ReplyKeyboard replyMarkup);

    Message sendDocument(File file, Boolean disableNotification);

    Message sendDocument(File file, Long chatId, String caption);

    Message sendDocument(File file, Long chatId, Integer replyMessageId);

    Message sendDocument(File file, Long chatId, ReplyKeyboard replyMarkup);

    Message sendDocument(File file, Long chatId, Boolean disableNotification);

    Message sendDocument(File file, String caption, Integer replyMessageId);

    Message sendDocument(File file, String caption, ReplyKeyboard replyMarkup);

    Message sendDocument(File file, String caption, Boolean disableNotification);

    Message sendDocument(File file, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendDocument(File file, Integer replyMessageId, Boolean disableNotification);

    Message sendDocument(File file, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendDocument(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendDocument(File file, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendDocument(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendDocument(File file, Long chatId, String caption, ReplyKeyboard replyMarkup);

    Message sendDocument(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendDocument(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendDocument(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    // Input Stream

    Message sendDocument(InputStream stream, String filename);

    Message sendDocument(InputStream stream, String filename, Long chatId);

    Message sendDocument(InputStream stream, String filename, String caption);

    Message sendDocument(InputStream stream, String filename, Integer replyMessageId);

    Message sendDocument(InputStream stream, String filename, ReplyKeyboard replyMarkup);

    Message sendDocument(InputStream stream, String filename, Boolean disableNotification);

    Message sendDocument(InputStream stream, String filename, Long chatId, String caption);

    Message sendDocument(InputStream stream, String filename, Long chatId, Integer replyMessageId);

    Message sendDocument(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup);

    Message sendDocument(InputStream stream, String filename, Long chatId, Boolean disableNotification);

    Message sendDocument(InputStream stream, String filename, String caption, Integer replyMessageId);

    Message sendDocument(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup);

    Message sendDocument(InputStream stream, String filename, String caption, Boolean disableNotification);

    Message sendDocument(InputStream stream, String filename, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendDocument(InputStream stream, String filename, Integer replyMessageId, Boolean disableNotification);

    Message sendDocument(InputStream stream, String filename, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendDocument(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendDocument(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendDocument(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendDocument(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup);

    Message sendDocument(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendDocument(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendDocument(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendDocument(SendDocument document);
}
