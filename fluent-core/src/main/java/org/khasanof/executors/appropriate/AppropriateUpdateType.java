package org.khasanof.executors.appropriate;

import org.khasanof.models.executors.AppropriateType;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate
 * @since 12/24/2023 7:39 PM
 */
public interface AppropriateUpdateType {

    boolean isMatch(Update update);

    AppropriateType getAppropriate(Update update);

    default boolean hasSubMethods() {
        return false;
    }

}
