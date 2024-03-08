package org.khasanof.factories.response.methods;

import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response.methods
 * @since 1/13/2024 11:55 PM
 */
public class ReturnMessageMethodsResponse extends AbstractExecMethodResponse {

    @Override
    public Object createResponse(Method method, Object[] args) {
        return createResponseInternal();
    }

    @NotNull
    private Message createResponseInternal() {
        Message message = new Message();
        message.setMessageId(RandomUtils.nextInt());
        message.setMessageThreadId(RandomUtils.nextInt());
        message.setMessageId(RandomUtils.nextInt());
        message.setMessageThreadId(RandomUtils.nextInt());
        return message;
    }

    @Override
    public Class<?> returnType() {
        return Message.class;
    }
}
