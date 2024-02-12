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

    Message sendText(String text, Long chatId, Integer replyMessageId);

    Message sendText(String text, Long chatId, ReplyKeyboard replyKeyboard);

    Message sendMessage(SendMessage message);
}
