package org.khasanof.executors.matcher.methods.message;

import org.khasanof.models.executors.AppropriateMethod;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher.methods.message
 * @since 12/24/2023 8:13 PM
 */
@Service
public class TextMessageMethodMatcher extends AbstractMessageMethodMatcher {

    @Override
    public boolean isMatch(Message message) {
        return Objects.equals(message.hasText(), Boolean.TRUE);
    }

    @Override
    public AppropriateMethod getAppropriate(Message message) {
        return new AppropriateMethod(handleType(), message.getText());
    }

}
