package com.example.fluenttest.custom.annotation;

import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.models.executors.AppropriateMethod;
import org.khasanof.models.executors.UpdateType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see com.example.fluenttest.custom
 * @since 2/20/2024 11:59 PM
 */
@Component
public class StickerMessageAppropriateMethod extends AppropriateUpdateMethod<Message> {

    @Override
    public boolean isMatch(Message message) {
        return message.hasSticker();
    }

    @Override
    public AppropriateMethod getAppropriate(Message message) {
        return new AppropriateMethod(HandleSticker.class, message.getSticker());
    }

    @Override
    public UpdateType handleType() {
        return UpdateType.MESSAGE;
    }
}
