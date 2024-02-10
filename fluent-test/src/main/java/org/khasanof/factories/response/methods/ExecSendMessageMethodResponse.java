package org.khasanof.factories.response.methods;

import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.NotNull;
import org.khasanof.service.template.operations.SendTextOperations;
import org.springframework.util.ReflectionUtils;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response.methods
 * @since 1/13/2024 11:55 PM
 */
public class ExecSendMessageMethodResponse extends AbstractExecMethodResponse {

    @Override
    public Object createResponse(Method method, Object[] args) {
        if (args[0] instanceof String text) {
            return createResponseInternal(text);
        }
        return EMPTY_OBJECT;
    }

    @NotNull
    private Message createResponseInternal(String text) {
        Message message = new Message();
        message.setText(text);
        message.setMessageId(RandomUtils.nextInt());
        message.setMessageThreadId(RandomUtils.nextInt());
        return message;
    }

    @Override
    public Class<? extends BotApiMethod> botApiMethod() {
        return SendMessage.class;
    }

    @Override
    public Method getMethod() {
        return ReflectionUtils.findMethod(SendTextOperations.class, "sendText", String.class);
    }
}
