package org.khasanof.service.template.operations.updatingmessages;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations.updatingmessages
 * @since 3/24/2024 12:29 PM
 */
public interface EditMessageCaptionOperations {

    Serializable editMessageCaption(Integer messageId, String caption);

    Serializable editMessageCaption(Integer messageId, String caption, Long chatId);

    Serializable editMessageCaption(String inlineMessageId, String caption);

    Serializable editMessageCaption(String inlineMessageId, String caption, Long chatId);

    Serializable editMessageCaption(Integer messageId, String caption, ReplyKeyboard replyKeyboard);

    Serializable editMessageCaption(Integer messageId, String caption, Long chatId, ReplyKeyboard replyKeyboard);

    Serializable editMessageCaption(Integer messageId, String caption, List<MessageEntity> captionEntities);

    Serializable editMessageCaption(Integer messageId, String caption, Long chatId, List<MessageEntity> captionEntities);

    Serializable editMessageCaption(Integer messageId, String caption, String parseMode, ReplyKeyboard replyKeyboard);

    Serializable editMessageCaption(Integer messageId, String caption, Long chatId, String parseMode, ReplyKeyboard replyKeyboard);

    Serializable editMessageCaption(Integer messageId, String caption, String parseMode, List<MessageEntity> captionEntities);

    Serializable editMessageCaption(Integer messageId, String caption, Long chatId, String parseMode, List<MessageEntity> captionEntities);

    Serializable editMessageCaption(Integer messageId, String caption, ReplyKeyboard replyKeyboard, List<MessageEntity> captionEntities);

    Serializable editMessageCaption(Integer messageId, String caption, Long chatId, ReplyKeyboard replyKeyboard, List<MessageEntity> captionEntities);

    Serializable editMessageCaption(EditMessageCaption editMessageCaption);
}
