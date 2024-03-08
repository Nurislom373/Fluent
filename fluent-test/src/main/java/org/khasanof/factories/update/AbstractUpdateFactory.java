package org.khasanof.factories.update;

import org.apache.commons.lang3.RandomUtils;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.function.Supplier;

/**
 * @author Nurislom
 * @see org.khasanof.factories.update
 * @since 1/10/2024 9:55 PM
 */
public abstract class AbstractUpdateFactory {

    protected Update createMessage(Supplier<Message> supplier) {
        Update update = createDefaultUpdate();
        update.setMessage(supplier.get());
        return update;
    }

    protected Update createCallbackQuery(Supplier<CallbackQuery> supplier) {
        Update update = createDefaultUpdate();
        update.setCallbackQuery(supplier.get());
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
        message.setChat(defaultChat());
        return message;
    }

    protected CallbackQuery createDefaultCallback() {
        CallbackQuery callbackQuery = new CallbackQuery();
        callbackQuery.setMessage(createDefaultMessage());
        callbackQuery.setId(String.valueOf(RandomUtils.nextLong()));
        callbackQuery.setFrom(defaultFrom());
        return callbackQuery;
    }

    protected Chat defaultChat() {
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
