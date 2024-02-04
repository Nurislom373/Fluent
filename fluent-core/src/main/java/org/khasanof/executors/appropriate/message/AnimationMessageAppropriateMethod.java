package org.khasanof.executors.appropriate.message;

import org.khasanof.enums.HandleType;
import org.khasanof.models.executors.AppropriateMethod;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.message
 * @since 12/24/2023 8:40 PM
 */
@Service
public class AnimationMessageAppropriateMethod extends AbstractMessageAppropriateMethod {

    @Override
    public boolean isMatch(Message message) {
        return tryIsMatch(message::hasAnimation);
    }

    @Override
    public AppropriateMethod getAppropriate(Message message) {
        return createAppropriateMethod(HandleType.ANIMATION, message.getAnimation());
    }
}
