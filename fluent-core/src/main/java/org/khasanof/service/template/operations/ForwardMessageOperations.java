package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;

/**
 * @author Rakh1sta
 * @see org.khasanof.service.template.operations
 * @since 2/15/2024 9:57 PM
 */
public interface ForwardMessageOperations {

    Message forwardMessage(String fromChatId, Integer messageId);

    Message forwardMessage(String fromChatId, Integer messageId, Long chatId);

    Message forwardMessage(ForwardMessage forwardMessage);
}
