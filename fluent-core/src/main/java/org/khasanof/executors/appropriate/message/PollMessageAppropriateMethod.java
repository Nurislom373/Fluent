package org.khasanof.executors.appropriate.message;

import org.khasanof.models.executors.AppropriateMethod;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.message
 * @since 12/25/2023 12:01 AM
 */
@Service
public class PollMessageAppropriateMethod extends AbstractMessageAppropriateMethod {

    @Override
    public boolean isMatch(Message message) {
        return tryIsMatch(message::hasPoll);
    }

    @Override
    public AppropriateMethod getAppropriate(Message message) {
        return tryGetAppropriate(message::getPoll);
    }

}