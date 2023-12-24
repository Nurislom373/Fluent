package org.khasanof.executors.matcher.methods;

import org.khasanof.models.executors.AppropriateType;
import org.khasanof.models.executors.UpdateType;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher.methods
 * @since 12/24/2023 7:42 PM
 */
@Service
public class MessageUpdateTypeMatcher implements UpdateTypeMatcher {

    @Override
    public boolean isMatch(Update update) {
        return Objects.equals(update.hasMessage(), Boolean.TRUE);
    }

    @Override
    public AppropriateType getAppropriate(Update update) {
        return new AppropriateType(UpdateType.MESSAGE, update.getMessage());
    }

    @Override
    public boolean hasSubMethods() {
        return true;
    }

}
