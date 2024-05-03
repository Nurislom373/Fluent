package org.khasanof.service.template.operations.pin;

import org.telegram.telegrambots.meta.api.methods.pinnedmessages.UnpinChatMessage;

/**
 * @author Rakh1sta
 * @see org.khasanof.service.template.operations.query
 * @since 4/03/2024 1:35 AM
 */
public interface UnpinChatMessageOperations {
    Boolean unpinChatMessage(Long chatId, Integer messageId);

    Boolean unpinChatMessage(UnpinChatMessage unpinChatMessage);
}
