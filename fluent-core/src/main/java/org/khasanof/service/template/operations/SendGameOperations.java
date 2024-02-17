package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendGame;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations
 * @since 2/17/2024 4:44 PM
 */
public interface SendGameOperations {

    Message sendGame(String gameShortName);

    Message sendGame(String gameShortName, Long chatId);

    Message sendGame(String gameShortName, Integer replyToMessageId);

    Message sendGame(String gameShortName, ReplyKeyboard replyKeyboard);

    Message sendGame(String gameShortName, Long chatId, Integer replyToMessageId);

    Message sendGame(String gameShortName, Long chatId, ReplyKeyboard replyKeyboard);

    Message sendGame(String gameShortName, Integer replyToMessageId, ReplyKeyboard replyKeyboard);

    Message sendGame(String gameShortName, Long chatId, Integer replyToMessageId, ReplyKeyboard replyKeyboard);

    Message sendGame(SendGame game);

}
