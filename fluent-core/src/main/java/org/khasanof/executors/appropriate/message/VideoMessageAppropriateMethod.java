package org.khasanof.executors.appropriate.message;

import org.khasanof.annotation.methods.HandleVideo;
import org.khasanof.models.executors.AppropriateMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.message
 * @since 12/25/2023 12:04 AM
 */
public class VideoMessageAppropriateMethod extends AbstractMessageAppropriateMethod {

    @Override
    public boolean isMatch(Message message) {
        return tryIsMatch(message::hasVideo);
    }

    @Override
    public AppropriateMethod getAppropriate(Message message) {
        return createAppropriateMethod(HandleVideo.class, message.getVideo());
    }
}
