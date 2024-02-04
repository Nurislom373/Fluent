package org.khasanof.executors.appropriate.message;

import org.khasanof.enums.HandleType;
import org.khasanof.models.executors.AppropriateMethod;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.message
 * @since 2/1/2024 9:35 PM
 */
@Service
public class WebAppDataAppropriateMethod extends AbstractMessageAppropriateMethod {

    @Override
    public boolean isMatch(Message message) {
        return Objects.nonNull(message.getWebAppData());
    }

    @Override
    public AppropriateMethod getAppropriate(Message message) {
        return createAppropriateMethod(HandleType.WEB_APP_DATA, message.getWebAppData());
    }
}
