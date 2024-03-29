package org.khasanof.executors.appropriate.message;

import org.khasanof.annotation.methods.HandlePhoto;
import org.khasanof.models.executors.AppropriateMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.message
 * @since 12/24/2023 11:57 PM
 */
public class PhotoMessageAppropriateMethod extends AbstractMessageAppropriateMethod {

    @Override
    public boolean isMatch(Message message) {
        return tryIsMatch(message::hasPhoto);
    }

    @Override
    public AppropriateMethod getAppropriate(Message message) {
        return new AppropriateMethod(HandlePhoto.class, message);
    }
}
