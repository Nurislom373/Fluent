package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations
 * @since 2/3/2024 11:09 PM
 */
public interface SendDiceOperations {

    Message sendDice(String emoji);

    Message sendDice(String emoji, Long chatId);

    Message sendDice(String emoji, Integer replyToMessageId);

    Message sendDice(String emoji, ReplyKeyboard replyMarkup);

    Message sendDice(String emoji, Long chatId, Integer replyMessageId);

    Message sendDice(String emoji, Long chatId, ReplyKeyboard replyKeyboard);

    Message sendDice(String emoji, ReplyKeyboard replyKeyboard, Integer replyMessageId);

    Message sendDice(String emoji, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendDice(SendDice sendDice);

}
