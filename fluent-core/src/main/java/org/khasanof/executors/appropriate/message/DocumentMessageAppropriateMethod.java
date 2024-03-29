package org.khasanof.executors.appropriate.message;

import org.khasanof.annotation.methods.HandleDocument;
import org.khasanof.models.executors.AppropriateMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.message
 * @since 12/24/2023 11:54 PM
 */
public class DocumentMessageAppropriateMethod extends AbstractMessageAppropriateMethod {

    @Override
    public boolean isMatch(Message message) {
        return tryIsMatch(message::hasDocument);
    }

    @Override
    public AppropriateMethod getAppropriate(Message message) {
        return createAppropriateMethod(HandleDocument.class, message);
    }
}
