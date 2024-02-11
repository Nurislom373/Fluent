package org.khasanof.factories.response.methods;

import org.khasanof.service.template.operations.callback.SendAnswerCallbackQueryOperations;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response.methods
 * @since 2/11/2024 9:13 PM
 */
public class SendAnswerCallbackQueryFirstMethodResponse extends AbstractExecMethodResponse {

    @Override
    public Object createResponse(Method method, Object[] args) {
        if (args[0] instanceof String && args[1] instanceof Boolean) {
            return Boolean.TRUE;
        }
        return EMPTY_OBJECT;
    }

    @Override
    public Method getMethod() {
        return ReflectionUtils.findMethod(SendAnswerCallbackQueryOperations.class, "sendAnswerCallbackQuery", String.class, Boolean.class);
    }
}
