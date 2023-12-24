package org.khasanof.executors.appropriate.message;

import org.khasanof.models.executors.AppropriateMethod;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.message
 * @since 12/24/2023 11:53 PM
 */
@Service
public class StickerMessageAppropriateMethod extends AbstractMessageAppropriateMethod {

    @Override
    public boolean isMatch(Message message) {
        return tryIsMatch(message::hasSticker);
    }

    @Override
    public AppropriateMethod getAppropriate(Message message) {
        return tryGetAppropriate(message::getSticker);
    }

}
