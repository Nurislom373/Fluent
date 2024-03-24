package org.khasanof.service.template.operations.updatingmessages;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations.updatingmessages
 * @since 3/24/2024 12:52 PM
 */
public interface EditMessageTextOperations {

    // message

    Serializable editMessageText(Integer messageId, String text);

    Serializable editMessageText(Integer messageId, String text, Long chatId);

    Serializable editMessageText(Integer messageId, String text, String parseMode);

    Serializable editMessageText(Integer messageId, String text, InlineKeyboardMarkup replyMarkup);

    Serializable editMessageText(Integer messageId, String text, Long chatId, String parseMode);

    Serializable editMessageText(Integer messageId, String text, Long chatId, InlineKeyboardMarkup replyMarkup);

    // inline message

    Serializable editMessageText(String inlineMessageId, String text);

    Serializable editMessageText(String inlineMessageId, String text, Long chatId);

    Serializable editMessageText(String inlineMessageId, String text, String parseMode);

    Serializable editMessageText(String inlineMessageId, String text, InlineKeyboardMarkup replyMarkup);

    Serializable editMessageText(String inlineMessageId, String text, Long chatId, String parseMode);

    Serializable editMessageText(String inlineMessageId, String text, Long chatId, InlineKeyboardMarkup replyMarkup);

    //

    Serializable editMessageText(EditMessageText editMessageText);
}
