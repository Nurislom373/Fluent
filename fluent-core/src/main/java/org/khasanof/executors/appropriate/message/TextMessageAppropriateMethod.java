package org.khasanof.executors.appropriate.message;

import org.khasanof.models.executors.AppropriateMethod;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher.methods.message
 * @since 12/24/2023 8:13 PM
 */
@Service
public class TextMessageAppropriateMethod extends AbstractMessageAppropriateMethod {

    @Override
    public boolean isMatch(Message message) {
        return tryIsMatch(message::hasText);
    }

    @Override
    public AppropriateMethod getAppropriate(Message message) {
        return tryGetAppropriate(message::getText);
    }

}