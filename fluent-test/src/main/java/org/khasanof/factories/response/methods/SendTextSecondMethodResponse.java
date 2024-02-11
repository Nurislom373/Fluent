package org.khasanof.factories.response.methods;

import org.khasanof.service.template.operations.SendTextOperations;
import org.springframework.util.ReflectionUtils;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response.methods
 * @since 2/11/2024 7:07 PM
 */
public class SendTextSecondMethodResponse extends AbstractExecMethodResponse {

    @Override
    public Object createResponse(Method method, Object[] args) throws Exception {
        if (args[0] instanceof String text && args[1] instanceof Long chatId) {

        }
        return EMPTY_OBJECT;
    }

    private Message createMessage(String text, Long chatId) {
        Message message = new Message();
        message.setText(text);
        message.setChat(createChat(chatId));
        return message;
    }

    private Chat createChat(Long chatId) {
        Chat chat = new Chat();
        chat.setId(chatId);
        return chat;
    }

    @Override
    public Method getMethod() {
        return ReflectionUtils.findMethod(SendTextOperations.class, "sendText", String.class, Long.class);
    }
}
