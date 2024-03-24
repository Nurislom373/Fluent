package org.khasanof.service.template.operations.updatingmessages;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations.updatingmessages
 * @since 3/24/2024 12:26 PM
 */
public interface DeleteMessageOperations {

    Boolean deleteMessage(Integer messageId);

    Boolean deleteMessage(Integer messageId, Long chatId);

    Boolean deleteMessage(DeleteMessage deleteMessage);
}
