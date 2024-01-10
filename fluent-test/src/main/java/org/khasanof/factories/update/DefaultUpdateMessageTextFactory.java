package org.khasanof.factories.update;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.factories.update
 * @since 1/9/2024 9:22 PM
 */
public class DefaultUpdateMessageTextFactory extends AbstractUpdateFactory implements UpdateMessageTextFactory {

    @Override
    public Update create(String text) {
        return create(() -> createMessage(text));
    }

    private Message createMessage(String text) {
        Message message = createDefaultMessage();
        message.setChat(chat());
        message.setText(text);
        return message;
    }
}
