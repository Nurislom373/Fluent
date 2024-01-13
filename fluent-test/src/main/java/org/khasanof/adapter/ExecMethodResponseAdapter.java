package org.khasanof.adapter;

import org.khasanof.factories.response.ExecMethodResponse;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response
 * @since 1/14/2024 12:01 AM
 */
public interface ExecMethodResponseAdapter {

    Object createResponse(Method method, Object[] args);

    void setExecMethodResponseMap(Map<Class<? extends BotApiMethod>, ExecMethodResponse> methodResponseMap);

}
