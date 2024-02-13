package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations
 * @since 2/3/2024 4:25 PM
 */
public interface SendAudioOperations {

    Message sendAudio(File file);

    Message sendAudio(File file, Long chatId);

    Message sendAudio(File file, String caption);

    Message sendAudio(File file, Integer replyMessageId);

    Message sendAudio(File file, ReplyKeyboard replyMarkup);

    Message sendAudio(File file, Boolean disableNotification);

    Message sendAudio(File file, Long chatId, String caption);

    Message sendAudio(File file, Long chatId, Integer replyMessageId);

    Message sendAudio(File file, Long chatId, ReplyKeyboard replyMarkup);

    Message sendAudio(File file, Long chatId, Boolean disableNotification);

    Message sendAudio(File file, String caption, Integer replyMessageId);

    Message sendAudio(File file, String caption, ReplyKeyboard replyMarkup);

    Message sendAudio(File file, String caption, Boolean disableNotification);

    Message sendAudio(File file, String caption, String title);

    Message sendAudio(File file, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendAudio(File file, Integer replyMessageId, Boolean disableNotification);

    Message sendAudio(File file, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendAudio(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendAudio(File file, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendAudio(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendAudio(File file, Long chatId, String caption, ReplyKeyboard replyMarkup);

    Message sendAudio(File file, Long chatId, String caption, String title, ReplyKeyboard replyMarkup);

    Message sendAudio(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendAudio(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendAudio(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendAudio(File file, Long chatId, String caption, String title, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    // Input Stream

    Message sendAudio(InputStream inputStream, String filename);

    Message sendAudio(InputStream inputStream, String filename, Long chatId);

    Message sendAudio(InputStream inputStream, String filename, String caption);

    Message sendAudio(InputStream inputStream, String filename, Integer replyMessageId);

    Message sendAudio(InputStream inputStream, String filename, ReplyKeyboard replyKeyboard);

    Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption);

    Message sendAudio(InputStream inputStream, String filename, Long chatId, Integer replyMessageId);

    Message sendAudio(InputStream inputStream, String filename, Long chatId, ReplyKeyboard replyKeyboard);

    Message sendAudio(InputStream inputStream, String filename, String caption, Integer replyMessageId);

    Message sendAudio(InputStream inputStream, String filename, String caption, ReplyKeyboard replyKeyboard);

    Message sendAudio(InputStream inputStream, String filename, String caption, String title);

    Message sendAudio(InputStream inputStream, String filename, String caption, String title, Integer replyMessageId);

    Message sendAudio(InputStream inputStream, String filename, String caption, String title, ReplyKeyboard replyKeyboard);

    Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption, String title);

    Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption, String title, Integer replyMessageId);

    Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption, String title, ReplyKeyboard replyKeyboard);

    Message sendAudio(SendAudio sendAudio);
}
