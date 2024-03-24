package org.khasanof.service.template.operations.updatingmessages;

import org.khasanof.models.Round;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageLiveLocation;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations.updatingmessages
 * @since 3/24/2024 12:38 PM
 */
public interface EditMessageLiveLocationOperations {

    Serializable editMessageLiveLocation(Integer messageId, Round round);

    Serializable editMessageLiveLocation(Integer messageId, Round round, Long chatId);

    Serializable editMessageLiveLocation(String inlineMessageId, Round round);

    Serializable editMessageLiveLocation(String inlineMessageId, Round round, Long chatId);

    Serializable editMessageLiveLocation(Integer messageId, Round round, ReplyKeyboard replyKeyboard);

    Serializable editMessageLiveLocation(Integer messageId, Round round, Long chatId, ReplyKeyboard replyKeyboard);

    Serializable editMessageLiveLocation(Integer messageId, Round round, Double horizontalAccuracy);

    Serializable editMessageLiveLocation(Integer messageId, Round round, Long chatId, Double horizontalAccuracy);

    Serializable editMessageLiveLocation(EditMessageLiveLocation editMessageLiveLocation);
}
