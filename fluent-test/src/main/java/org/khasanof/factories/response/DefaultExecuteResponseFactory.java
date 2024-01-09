package org.khasanof.factories.response;

import org.apache.commons.lang3.RandomUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response
 * @since 1/9/2024 10:49 PM
 */
public class DefaultExecuteResponseFactory implements ExecuteResponseFactory {

    @Override
    public Message create(SendMessage sendMessage) {
        Message message = new Message();
        message.setMessageId(RandomUtils.nextInt());
        message.setText(sendMessage.getText());
        message.setMessageThreadId(sendMessage.getMessageThreadId());
        return message;
    }

}
