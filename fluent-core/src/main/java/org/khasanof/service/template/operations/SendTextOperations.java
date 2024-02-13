package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations
 * @since 1/23/2024 9:29 PM
 */
public interface SendTextOperations {

    Message sendText(String text);

    Message sendText(String text, Long chatId);

    Message sendText(String text, Integer replyMessageId);

    Message sendText(String text, ReplyKeyboard replyKeyboard);

    Message sendText(String text, Boolean disableNotification);

    Message sendTextWithParseMode(String text, String parseMode);

    Message sendText(String text, Long chatId, Integer replyMessageId);

    Message sendText(String text, Long chatId, ReplyKeyboard replyKeyboard);

    Message sendText(String text, Long chatId, Boolean disableNotification);

    Message sendText(String text, Long chatId, String parseMode);

    Message sendText(String text, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendText(String text, Integer replyMessageId, Boolean disableNotification);

    Message sendText(String text, Integer replyMessageId, String parseMode);

    Message sendText(String text, ReplyKeyboard replyKeyboard, Boolean disableNotification);

    Message sendText(String text, ReplyKeyboard replyKeyboard, String parseMode);

    Message sendText(String text, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendText(String text, Long chatId, Integer replyMessageId, Boolean disableNotification);

    Message sendText(String text, Long chatId, Integer replyMessageId, String parseMode);

    Message sendText(String text, Long chatId, ReplyKeyboard replyKeyboard, Boolean disableNotification);

    Message sendText(String text, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String parseMode);

    Message sendText(String text, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String parseMode, Boolean disableNotification);

    Message sendMessage(SendMessage message);
}
