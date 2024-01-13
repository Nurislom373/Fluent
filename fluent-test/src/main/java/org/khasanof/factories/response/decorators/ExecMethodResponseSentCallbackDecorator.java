package org.khasanof.factories.response.decorators;

import org.khasanof.factories.response.ExecMethodResponse;
import org.khasanof.factories.response.ExecMethodResponseDecorator;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;
import org.telegram.telegrambots.meta.updateshandlers.SentCallback;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response.decorators
 * @since 1/13/2024 11:33 PM
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ExecMethodResponseSentCallbackDecorator extends ExecMethodResponseDecorator {

    public ExecMethodResponseSentCallbackDecorator(ExecMethodResponse executeMethodResponse) {
        super(executeMethodResponse);
    }

    @Override
    public Object createResponse(Method method, Object[] args) {
        return createResponseInternal(method, args, (SentCallback) args[1], (BotApiMethod) args[0]);
    }

    private Object createResponseInternal(Method method, Object[] args, SentCallback sentCallback, BotApiMethod botApiMethod) {
        Object response = null;
        try {
            response = super.createResponse(method, args);
            sentCallback.onResult(botApiMethod, (Serializable) response);
        } catch (Exception e) {
            if (e instanceof TelegramApiRequestException requestException) {
                sentCallback.onError(botApiMethod, requestException);
            } else {
                sentCallback.onException(botApiMethod, e);
            }
        }
        return response;
    }

}
