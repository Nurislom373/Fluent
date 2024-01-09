package org.khasanof.factories.response;

import org.khasanof.factories.GenericFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response
 * @since 1/9/2024 10:49 PM
 */
public interface ExecuteResponseFactory extends GenericFactory<SendMessage, Message> {

}
