package org.khasanof.executors.appropriate.type;

import org.khasanof.executors.appropriate.AppropriateUpdateType;
import org.khasanof.models.executors.AppropriateType;
import org.khasanof.models.executors.UpdateType;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.type
 * @since 1/2/2024 7:10 PM
 */
public class MyChatMemberTypeMatcher implements AppropriateUpdateType {

    @Override
    public boolean isMatch(Update update) {
        return Objects.equals(update.hasMyChatMember(), Boolean.TRUE);
    }

    @Override
    public AppropriateType getAppropriate(Update update) {
        return new AppropriateType(UpdateType.MY_CHAT_MEMBER, update.getMyChatMember(), hasSubMethods());
    }
}
