package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;

/**
 * @author Abdulloh
 * @see org.khasanof.service.template.operations
 * @since 2/15/2024 10:40 PM
 */

public interface SendChatActionOperations {

    Boolean sendChatAction(String action);

    Boolean sendChatAction(Long chatId, String action);

    Boolean sendChatAction(Long chatId, String action, Integer messageThreadId);

    Boolean sendChatAction(SendChatAction sendChatAction);


}
