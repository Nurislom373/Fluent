package org.khasanof.service.template.operations.pin;

import org.telegram.telegrambots.meta.api.methods.pinnedmessages.UnpinAllChatMessages;

/**
 * @author Rakh1sta
 * @see org.khasanof.service.template.operations.query
 * @since 4/03/2024 1:35 AM
 */
public interface UnpinAllChatMessagesOperations {
    Boolean unpinAllChatMessages(Long chatId);
    Boolean unpinAllChatMessages(UnpinAllChatMessages unpinAllChatMessages);
}
