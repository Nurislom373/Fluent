package org.khasanof.executors.matcher.methods.message;

import org.khasanof.enums.HandleType;
import org.khasanof.executors.matcher.methods.UpdateMethodMatcher;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher.methods.message
 * @since 12/24/2023 8:39 PM
 */
public abstract class AbstractMessageMethodMatcher extends UpdateMethodMatcher<Message> {

    @Override
    public HandleType handleType() {
        return HandleType.MESSAGE;
    }

}
