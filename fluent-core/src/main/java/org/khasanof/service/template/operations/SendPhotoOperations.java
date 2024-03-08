package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations
 * @since 2/12/2024 8:30 PM
 */
public interface SendPhotoOperations {

    Message sendPhoto(File file);

    Message sendPhoto(File file, Long chatId);

    Message sendPhoto(File file, String caption);

    Message sendPhoto(File file, Integer replyMessageId);

    Message sendPhoto(File file, ReplyKeyboard replyMarkup);

    Message sendPhoto(File file, Boolean disableNotification);

    Message sendPhoto(File file, Long chatId, String caption);

    Message sendPhoto(File file, Long chatId, Integer replyMessageId);

    Message sendPhoto(File file, Long chatId, ReplyKeyboard replyMarkup);

    Message sendPhoto(File file, Long chatId, Boolean disableNotification);

    Message sendPhoto(File file, String caption, Integer replyMessageId);

    Message sendPhoto(File file, String caption, ReplyKeyboard replyMarkup);

    Message sendPhoto(File file, String caption, Boolean disableNotification);

    Message sendPhoto(File file, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendPhoto(File file, Integer replyMessageId, Boolean disableNotification);

    Message sendPhoto(File file, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendPhoto(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendPhoto(File file, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendPhoto(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendPhoto(File file, Long chatId, String caption, ReplyKeyboard replyMarkup);

    Message sendPhoto(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendPhoto(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendPhoto(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    // Input Stream

    Message sendPhoto(InputStream stream, String filename);

    Message sendPhoto(InputStream stream, String filename, Long chatId);

    Message sendPhoto(InputStream stream, String filename, String caption);

    Message sendPhoto(InputStream stream, String filename, Integer replyMessageId);

    Message sendPhoto(InputStream stream, String filename, ReplyKeyboard replyMarkup);

    Message sendPhoto(InputStream stream, String filename, Boolean disableNotification);

    Message sendPhoto(InputStream stream, String filename, Long chatId, String caption);

    Message sendPhoto(InputStream stream, String filename, Long chatId, Integer replyMessageId);

    Message sendPhoto(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup);

    Message sendPhoto(InputStream stream, String filename, Long chatId, Boolean disableNotification);

    Message sendPhoto(InputStream stream, String filename, String caption, Integer replyMessageId);

    Message sendPhoto(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup);

    Message sendPhoto(InputStream stream, String filename, String caption, Boolean disableNotification);

    Message sendPhoto(InputStream stream, String filename, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendPhoto(InputStream stream, String filename, Integer replyMessageId, Boolean disableNotification);

    Message sendPhoto(InputStream stream, String filename, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendPhoto(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup);

    Message sendPhoto(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendPhoto(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendPhoto(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendPhoto(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendPhoto(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendPhoto(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendPhoto(SendPhoto photo);
}
