package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;

/**
 * @author Abdulloh
 * @see org.khasanof.service.template.operations
 * @since 2/15/2024 10:39 PM
 */

public interface SendAnimationOperations {

    Message sendAnimation(File file);

    Message sendAnimation(File file, Long chatId);

    Message sendAnimation(File file, String caption);

    Message sendAnimation(File file, Integer replyMessageId);

    Message sendAnimation(File file, ReplyKeyboard replyMarkup);

    Message sendAnimation(File file, Boolean disableNotification);

    Message sendAnimation(File file, Long chatId, String caption);

    Message sendAnimation(File file, Long chatId, Integer replyMessageId);

    Message sendAnimation(File file, Long chatId, ReplyKeyboard replyMarkup);

    Message sendAnimation(File file, Long chatId, Boolean disableNotification);

    Message sendAnimation(File file, String caption, Integer replyMessageId);

    Message sendAnimation(File file, String caption, ReplyKeyboard replyMarkup);

    Message sendAnimation(File file, String caption, Boolean disableNotification);

    Message sendAnimation(File file, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendAnimation(File file, Integer replyMessageId, Boolean disableNotification);

    Message sendAnimation(File file, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendAnimation(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendAnimation(File file, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendAnimation(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendAnimation(File file, Long chatId, String caption, ReplyKeyboard replyMarkup);

    Message sendAnimation(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendAnimation(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendAnimation(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    // Input Stream

    Message sendAnimation(InputStream stream, String filename);

    Message sendAnimation(InputStream stream, String filename, Long chatId);

    Message sendAnimation(InputStream stream, String filename, String caption);

    Message sendAnimation(InputStream stream, String filename, Integer replyMessageId);

    Message sendAnimation(InputStream stream, String filename, ReplyKeyboard replyMarkup);

    Message sendAnimation(InputStream stream, String filename, Boolean disableNotification);

    Message sendAnimation(InputStream stream, String filename, Long chatId, String caption);

    Message sendAnimation(InputStream stream, String filename, Long chatId, Integer replyMessageId);

    Message sendAnimation(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup);

    Message sendAnimation(InputStream stream, String filename, Long chatId, Boolean disableNotification);

    Message sendAnimation(InputStream stream, String filename, String caption, Integer replyMessageId);

    Message sendAnimation(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup);

    Message sendAnimation(InputStream stream, String filename, String caption, Boolean disableNotification);

    Message sendAnimation(InputStream stream, String filename, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendAnimation(InputStream stream, String filename, Integer replyMessageId, Boolean disableNotification);

    Message sendAnimation(InputStream stream, String filename, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendAnimation(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup);

    Message sendAnimation(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendAnimation(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendAnimation(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendAnimation(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendAnimation(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification);

    Message sendAnimation(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification);

    Message sendAnimation(SendAnimation animation);

}
