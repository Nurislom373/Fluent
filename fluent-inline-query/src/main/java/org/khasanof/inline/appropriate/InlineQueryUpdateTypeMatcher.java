package org.khasanof.inline.appropriate;

import org.khasanof.executors.appropriate.AppropriateUpdateType;
import org.khasanof.models.executors.AppropriateType;
import org.khasanof.models.executors.UpdateType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.type
 * @since 12/24/2023 7:51 PM
 */
@Component
public class InlineQueryUpdateTypeMatcher implements AppropriateUpdateType {

    @Override
    public boolean isMatch(Update update) {
        return Objects.equals(update.hasInlineQuery(), Boolean.TRUE);
    }

    @Override
    public AppropriateType getAppropriate(Update update) {
        return new AppropriateType(UpdateType.INLINE_QUERY, update.getInlineQuery(), hasSubMethods());
    }
}
