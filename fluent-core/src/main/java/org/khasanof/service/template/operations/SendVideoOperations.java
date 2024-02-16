package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;

/**
 * @author Rakh1sta
 * @see org.khasanof.service.template.operations
 * @since 2/15/2024 9:57 PM
 */
public interface SendVideoOperations {

    Message sendVideo(File file);

    Message sendVideo(File file, Long chatId);

    Message sendVideo(File file, String caption);

    Message sendVideo(File file, Integer replyToMessageId);

    Message sendVideo(File file, ReplyKeyboard replyMarkup);

    Message sendVideo(File file, Long chatId, String caption);

    Message sendVideo(File file, Long chatId, Integer replyMessageId);

    Message sendVideo(File file, Long chatId, ReplyKeyboard replyMarkup);

    Message sendVideo(File file, String caption, Integer replyMessageId);

    Message sendVideo(File file, String caption, ReplyKeyboard replyMarkup);

    Message sendVideo(File file, Integer replyToMessageId, ReplyKeyboard replyMarkup);

    Message sendVideo(File file, Long chatId, String caption, Integer replyMessageId);

    Message sendVideo(File file, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendVideo(File file, Long chatId, String caption, ReplyKeyboard replyMarkup);

    Message sendVideo(File file, String caption, Integer replyMessageId, ReplyKeyboard replyMarkup);

    Message sendVideo(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    // Input Stream

    Message sendVideo(InputStream stream, String filename);

    Message sendVideo(InputStream stream, String filename, Long chatId);

    Message sendVideo(InputStream stream, String filename, String caption);

    Message sendVideo(InputStream stream, String filename, Integer replyToMessageId);

    Message sendVideo(InputStream stream, String filename, ReplyKeyboard replyMarkup);

    Message sendVideo(InputStream stream, String filename, Long chatId, String caption);

    Message sendVideo(InputStream stream, String filename, Long chatId, Integer replyMessageId);

    Message sendVideo(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup);

    Message sendVideo(InputStream stream, String filename, String caption, Integer replyMessageId);

    Message sendVideo(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup);

    Message sendVideo(InputStream stream, String filename, Integer replyToMessageId, ReplyKeyboard replyMarkup);

    Message sendVideo(InputStream stream, String filename, Long chatId, String caption, Integer replyMessageId);

    Message sendVideo(InputStream stream, String filename, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendVideo(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup);

    Message sendVideo(InputStream stream, String filename, String caption, Integer replyMessageId, ReplyKeyboard replyMarkup);

    Message sendVideo(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendVideo(SendVideo sendVideo);
}


