package org.khasanof.executors.appropriate.type;

import org.khasanof.executors.appropriate.AppropriateUpdateType;
import org.khasanof.models.executors.AppropriateType;
import org.khasanof.models.executors.UpdateType;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.type
 * @since 12/24/2023 7:49 PM
 */
public class CallbackUpdateTypeMatcher implements AppropriateUpdateType {

    @Override
    public boolean isMatch(Update update) {
        return Objects.equals(update.hasCallbackQuery(), Boolean.TRUE);
    }

    @Override
    public AppropriateType getAppropriate(Update update) {
        return new AppropriateType(UpdateType.CALLBACK, update.getCallbackQuery().getData(), hasSubMethods());
    }

}
