package org.khasanof.executors.matcher.chat;

import org.khasanof.annotation.methods.chat.HandleMyChatMember;
import org.khasanof.config.ApplicationConstants;
import org.khasanof.executors.matcher.GenericMatcher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher.chat
 * @since 1/2/2024 7:21 PM
 */
@Component
public class DefaultMyChatMemberMatcher extends GenericMatcher<HandleMyChatMember, ChatMemberUpdated> {

    public DefaultMyChatMemberMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleMyChatMember annotation, ChatMemberUpdated value) {
        return true; // because matcher logic write will be soon!
    }

    @Override
    public Class<HandleMyChatMember> getType() {
        return HandleMyChatMember.class;
    }
}
