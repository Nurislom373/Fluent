package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;

/**
 * @author Abdulloh
 * @see org.khasanof.service.template.operations
 * @since 2/15/2024 10:38 PM
 */

public interface SendStickerOperations {
    Message sendSticker(File file);

    Message sendSticker(File file, Long chatId);

    Message sendSticker(File file, String emoji);

    Message sendSticker(File file, Integer replyMessageId);

    Message sendSticker(File file, ReplyKeyboard replyMarkup);

    Message sendSticker(File file, Boolean disableNotification);

    Message sendSticker(File file, Long chatId, String emoji);

    Message sendSticker(File file, Long chatId, Integer replyMessageId);

    Message sendSticker(File file, Long chatId, ReplyKeyboard replyMarkup);

    Message sendSticker(File file, Long chatId, Boolean disableNotification);

    Message sendSticker(File file, String emoji, Integer replyMessageId);

    Message sendSticker(File file, String emoji, ReplyKeyboard replyMarkup);

    Message sendSticker(File file, String emoji, Boolean disableNotification);

    Message sendSticker(File file, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendSticker(File file, Integer replyMessageId, Boolean disableNotification);

    Message sendSticker(File file, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendSticker(File file, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendSticker(File file, String emoji, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendSticker(File file, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendSticker(File file, Long chatId, String emoji, ReplyKeyboard replyMarkup);

    Message sendSticker(File file, Long chatId, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendSticker(File file, Long chatId, String emoji, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendSticker(File file, Long chatId, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    // Input Stream

    Message sendSticker(InputStream stream, String filename);

    Message sendSticker(InputStream stream, String filename, Long chatId);

    Message sendSticker(InputStream stream, String filename, String emoji);

    Message sendSticker(InputStream stream, String filename, Integer replyMessageId);

    Message sendSticker(InputStream stream, String filename, ReplyKeyboard replyMarkup);

    Message sendSticker(InputStream stream, String filename, Boolean disableNotification);

    Message sendSticker(InputStream stream, String filename, Long chatId, String emoji);

    Message sendSticker(InputStream stream, String filename, Long chatId, Integer replyMessageId);

    Message sendSticker(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup);

    Message sendSticker(InputStream stream, String filename, Long chatId, Boolean disableNotification);

    Message sendSticker(InputStream stream, String filename, String emoji, Integer replyMessageId);

    Message sendSticker(InputStream stream, String filename, String emoji, ReplyKeyboard replyMarkup);

    Message sendSticker(InputStream stream, String filename, String emoji, Boolean disableNotification);

    Message sendSticker(InputStream stream, String filename, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendSticker(InputStream stream, String filename, Integer replyMessageId, Boolean disableNotification);

    Message sendSticker(InputStream stream, String filename, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendSticker(InputStream stream, String filename, Long chatId, String emoji, ReplyKeyboard replyMarkup);

    Message sendSticker(InputStream stream, String filename, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendSticker(InputStream stream, String filename, String emoji, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendSticker(InputStream stream, String filename, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendSticker(InputStream stream, String filename, Long chatId, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendSticker(InputStream stream, String filename, Long chatId, String emoji, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendSticker(InputStream stream, String filename, Long chatId, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendSticker(SendSticker sticker);


}
