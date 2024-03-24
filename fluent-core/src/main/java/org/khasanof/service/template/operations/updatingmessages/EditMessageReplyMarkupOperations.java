package org.khasanof.service.template.operations.updatingmessages;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations.updatingmessages
 * @since 3/24/2024 12:50 PM
 */
public interface EditMessageReplyMarkupOperations {

    Serializable editMessageReplyMarkup(Integer messageId, InlineKeyboardMarkup replyMarkup);

    Serializable editMessageReplyMarkup(Integer messageId, InlineKeyboardMarkup replyMarkup, Long chatId);

    Serializable editMessageReplyMarkup(String inlineMessageId, InlineKeyboardMarkup replyMarkup);

    Serializable editMessageReplyMarkup(String inlineMessageId, InlineKeyboardMarkup replyMarkup, Long chatId);

    Serializable editMessageReplyMarkup(EditMessageReplyMarkup editMessageReplyMarkup);
}
