package org.khasanof.adapter;

import org.khasanof.factories.response.ExecMethodResponse;
import org.khasanof.factories.response.decorators.ExecMethodResponseSentCallbackDecorator;
import org.khasanof.factories.response.decorators.ExecMethodResponseWrapFutureDecorator;
import org.khasanof.factories.response.methods.AbstractExecMethodResponse;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response
 * @since 1/14/2024 12:02 AM
 */
@SuppressWarnings({"rawtypes"})
public class DefaultExeMethodResponseAdapter implements ExecMethodResponseAdapter {

    private final Map<Class<? extends BotApiMethod>, ExecMethodResponse> methodResponseMap = new HashMap<>();

    @Override
    public Object createResponse(Method method, Object[] args) {
        if (args.length <= 2) {
            return createResponseInternal(method, args);
        }
        throw new RuntimeException("Arguments greater than two!");
    }

    @Override
    public void setExecMethodResponseMap(Map<Class<? extends BotApiMethod>, ExecMethodResponse> methodResponseMap) {
        if (Objects.nonNull(methodResponseMap) && !methodResponseMap.isEmpty()) {
            this.methodResponseMap.putAll(methodResponseMap);
        }
    }

    private Object createResponseInternal(Method method, Object[] args) {
        try {
            return tryCreateResponseInternal(method, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object tryCreateResponseInternal(Method method, Object[] args) throws Exception {
        if (args.length == 1) {
            return createResponseOneArgument(method, args);
        } else {
            return createResponseTwoArgument(method, args);
        }
    }

    private Object createResponseTwoArgument(Method method, Object[] args) throws Exception {
        if (args[0] instanceof BotApiMethod botApiMethod) {
            ExecMethodResponse execMethodResponse = getExecMethodResponseSentCallback(botApiMethod);
            return execMethodResponse.createResponse(method, args);
        }
        throw new RuntimeException();
    }

    private ExecMethodResponse getExecMethodResponseSentCallback(BotApiMethod botApiMethod) {
        return new ExecMethodResponseSentCallbackDecorator(this.methodResponseMap.get(botApiMethod));
    }

    private Object createResponseOneArgument(Method method, Object[] args) throws Exception {
        if (args[0] instanceof BotApiMethod botApiMethod) {
            ExecMethodResponse execMethodResponse = getExecMethodResponseWrapFuture(method, botApiMethod);
            return execMethodResponse.createResponse(method, args);
        }
        throw new RuntimeException();
    }

    private ExecMethodResponse getExecMethodResponseWrapFuture(Method method, BotApiMethod botApiMethod) {
        ExecMethodResponse execMethodResponse = this.methodResponseMap.get(botApiMethod.getClass());

        if (isAsync(method)) {
            execMethodResponse = new ExecMethodResponseWrapFutureDecorator(execMethodResponse);
        }
        return execMethodResponse;
    }

    private boolean isAsync(Method method) {
        return method.getName().endsWith("Async");
    }
}
