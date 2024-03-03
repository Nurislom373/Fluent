package org.khasanof.utils;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 12.07.2023 23:45
 */
public abstract class UpdateUtils {

    public static Long getUserId(Update update) {
        User from = getFrom(update);
        return from != null ? from.getId() : null;
    }

    /**
     * Get chat id from update.
     *
     * @param update {@code Update}
     * @return chat id
     */
    public static Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        if (update.hasChatJoinRequest()) {
            return update.getChatJoinRequest().getChat().getId();
        }
        if (update.hasEditedMessage()) {
            return update.getEditedMessage().getChatId();
        }
        if (update.hasEditedChannelPost()) {
            return update.getEditedChannelPost().getChatId();
        }
        if (update.hasMyChatMember()) {
            return update.getMyChatMember().getChat().getId();
        }
        if (update.hasChatMember()) {
            return update.getChatMember().getChat().getId();
        }
        if (update.hasChannelPost()) {
            return update.getChannelPost().getChatId();
        }
        return null;
    }

    /**
     * Get callback query id from update.
     *
     * @param update {@code Update}
     * @return callback query id
     */
    public static String getCallbackId(Update update) {
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getId();
        }
        return null;
    }

    /**
     * Get inline query id from update.
     *
     * @param update {@code Update}
     * @return inline query id
     */
    public static String getInlineQueryId(Update update) {
        if (update.hasInlineQuery()) {
            return update.getInlineQuery().getId();
        }
        return null;
    }

    /**
     *
     * @param update
     * @return
     */
    public static User getFrom(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom();
        }
        if (update.hasEditedMessage()) {
            return update.getEditedMessage().getFrom();
        }
        if (update.hasEditedChannelPost()) {
            return update.getEditedChannelPost().getFrom();
        }
        if (update.hasInlineQuery()) {
            return update.getInlineQuery().getFrom();
        }
        if (update.hasChosenInlineQuery()) {
            return update.getChosenInlineQuery().getFrom();
        }
        if (update.hasMyChatMember()) {
            return update.getChatMember().getFrom();
        }
        if (update.hasChannelPost()) {
            return update.getChannelPost().getFrom();
        }
        if (update.hasShippingQuery()) {
            return update.getShippingQuery().getFrom();
        }
        if (update.hasPreCheckoutQuery()) {
            return update.getPreCheckoutQuery().getFrom();
        }
        if (update.hasPollAnswer()) {
            return update.getPollAnswer().getUser();
        }
        if (update.hasMyChatMember()) {
            return update.getMyChatMember().getFrom();
        }
        if (update.hasChatMember()) {
            return update.getChatMember().getFrom();
        }
        if (update.hasChatJoinRequest()) {
            return update.getChatJoinRequest().getUser();
        }
        return null;
    }

    public static String getFileId(Update update) {
        Map<Function<Message, Boolean>, Function<Message, Object>> functionMap = new HashMap<>() {{
            put((message -> message.getClass().equals(Message.class)), (Message::getDocument));
            put((Message::hasAudio), (Message::getAudio));
            put((Message::hasPhoto), (message -> message.getPhoto().get(0)));
            put((Message::hasVideo), (Message::getVideo));
            put((Message::hasVideoNote), (Message::getVideoNote));
        }};
        return getListMatchFunctionValue(update.getMessage(), functionMap, "fileId");
    }

    @SneakyThrows
    public static InputStream getInputStreamWithFileId(String id, TelegramLongPollingBot bot) {
        GetFile file = new GetFile();
        file.setFileId(id);
        File executed = bot.execute(file);
        return new URL(executed.getFileUrl(bot.getBotToken()))
                .openStream();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getListMatchFunctionValue(Message message, Map<Function<Message, Boolean>, Function<Message, Object>> functionMap,
                                                   String fieldName) {
        return (T) functionMap.entrySet()
                .stream()
                .filter(functionFunctionEntry -> functionFunctionEntry.getKey().apply(message))
                .findFirst()
                .map(functionFunctionEntry -> getObjField(functionFunctionEntry.getValue().apply(message), fieldName))
                .orElse(null);
    }

    public static <T, R> R getListMatchFunctionValue(T message, Map<Function<T, Boolean>, Function<T, R>> functionMap) {
        return functionMap.entrySet().stream().filter(functionFunctionEntry ->
                        functionFunctionEntry.getKey().apply(message)).findFirst()
                .map(functionFunctionEntry -> functionFunctionEntry.getValue().apply(message))
                .orElse(null);
    }

    @SneakyThrows
    private static Object getObjField(Object obj, String fieldName) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().equals(fieldName)) {
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            }
        }
        throw new RuntimeException("field not found!");
    }

}
