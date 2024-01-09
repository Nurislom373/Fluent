package org.khasanof.factories.update;

import org.apache.commons.lang3.RandomUtils;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.khasanof.utils.BaseUtils.defaultFrom;

/**
 * @author Nurislom
 * @see org.khasanof.factories.update
 * @since 1/9/2024 9:22 PM
 */
public class DefaultUpdateMessageFactory implements UpdateMessageFactory {

    @Override
    public Update create(String text) {
        Update update = new Update();
        update.setUpdateId(RandomUtils.nextInt());
        update.setMessage(createMessage(text));
        return update;
    }

    private Message createMessage(String text) {
        Message message = new Message();
        message.setChat(chat());
        message.setMessageId(RandomUtils.nextInt());
        message.setText(text);
        message.setFrom(this.defaultFrom());
        return message;
    }

    private Chat chat() {
        Chat chat = new Chat();
        chat.setId(RandomUtils.nextLong());
        return chat;
    }

    private User defaultFrom() {
        User user = new User();
        user.setId(RandomUtils.nextLong());
        user.setUserName("fluent");
        user.setFirstName("Fluent");
        user.setIsPremium(true);
        user.setIsBot(false);
        user.setLanguageCode("EN");
        user.setCanJoinGroups(true);
        return user;
    }

}
