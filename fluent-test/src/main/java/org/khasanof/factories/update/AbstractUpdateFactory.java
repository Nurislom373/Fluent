package org.khasanof.factories.update;

import org.apache.commons.lang3.RandomUtils;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.function.Supplier;

/**
 * @author Nurislom
 * @see org.khasanof.factories.update
 * @since 1/10/2024 9:55 PM
 */
public abstract class AbstractUpdateFactory {

    protected Update create(Supplier<Message> supplier) {
        Update update = createDefaultUpdate();
        update.setMessage(supplier.get());
        return update;
    }

    protected Update createDefaultUpdate() {
        Update update = new Update();
        update.setUpdateId(RandomUtils.nextInt());
        return update;
    }

    protected Message createDefaultMessage() {
        Message message = new Message();
        message.setMessageId(RandomUtils.nextInt());
        message.setFrom(defaultFrom());
        return message;
    }

    protected Chat chat() {
        Chat chat = new Chat();
        chat.setId(RandomUtils.nextLong());
        return chat;
    }

    protected User defaultFrom() {
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
