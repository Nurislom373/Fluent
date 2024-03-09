package org.khasanof.inline.appropriate;

import org.khasanof.executors.appropriate.AppropriateUpdateType;
import org.khasanof.models.executors.AppropriateType;
import org.khasanof.models.executors.UpdateType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.inline.appropriate
 * @since 3/9/2024 12:30 PM
 */
@Component
public class ChosenInlineQueryUpdateTypeMatcher implements AppropriateUpdateType {

    @Override
    public boolean isMatch(Update update) {
        return Objects.equals(update.hasChosenInlineQuery(), Boolean.TRUE);
    }

    @Override
    public AppropriateType getAppropriate(Update update) {
        return new AppropriateType(UpdateType.CHOSEN_INLINE_QUERY, update.getChosenInlineQuery(), hasSubMethods());
    }
}
