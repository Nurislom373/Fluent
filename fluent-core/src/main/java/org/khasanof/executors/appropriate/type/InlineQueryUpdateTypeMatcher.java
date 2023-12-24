package org.khasanof.executors.appropriate.type;

import org.khasanof.executors.appropriate.AppropriateUpdateType;
import org.khasanof.models.executors.AppropriateType;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher.methods
 * @since 12/24/2023 7:51 PM
 */
@Service
public class InlineQueryUpdateTypeMatcher implements AppropriateUpdateType {

    @Override
    public boolean isMatch(Update update) {
        return Objects.equals(update.hasInlineQuery(), Boolean.TRUE);
    }

    @Override
    public AppropriateType getAppropriate(Update update) {
        return new AppropriateType(); // TODO inline query
    }

}
