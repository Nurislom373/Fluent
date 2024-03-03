package org.khasanof.service.template.operations.pin;

import org.telegram.telegrambots.meta.api.methods.pinnedmessages.PinChatMessage;

/**
 * @author Rakh1sta
 * @see org.khasanof.service.template.operations.query
 * @since 4/03/2024 1:35 AM
 */
public interface PinChatMessageOperations {
    Boolean pinChatMessage(Long chatId, Integer messageId);
    Boolean pinChatMessage(Long chatId, Integer messageId, Boolean disableNotification);
    Boolean pinChatMessage(PinChatMessage pinChatMessage);
}
