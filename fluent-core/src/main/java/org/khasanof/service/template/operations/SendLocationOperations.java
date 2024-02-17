package org.khasanof.service.template.operations;

import org.khasanof.models.Round;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations
 * @since 2/17/2024 4:55 PM
 */
public interface SendLocationOperations {

    Message sendLocation(Round round);

    Message sendLocation(Round round, Long chatId);

    Message sendLocation(Round round, Integer replyToMessageId);

    Message sendLocation(Round round, ReplyKeyboard replyMarkup);

    Message sendLocation(Round round, Long chatId, Integer replyToMessageId);

    Message sendLocation(Round round, Long chatId, ReplyKeyboard replyMarkup);

    Message sendLocation(Round round, Integer replyToMessageId, ReplyKeyboard replyMarkup);

    Message sendLocation(Round round, Long chatId, Integer replyToMessageId, ReplyKeyboard replyMarkup);

    Message sendLocation(SendLocation location);
}
