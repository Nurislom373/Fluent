package org.khasanof.executors.matcher.methods;

import org.khasanof.models.executors.AppropriateType;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher.methods
 * @since 12/24/2023 7:39 PM
 */
public interface UpdateTypeMatcher {

    boolean isMatch(Update update);

    AppropriateType getAppropriate(Update update);

    default boolean hasSubMethods() {
        return false;
    }

}
