package org.khasanof.adapter;

import org.apache.commons.lang3.RandomUtils;
import org.khasanof.factories.response.ExecMethodResponse;
import org.khasanof.factories.response.decorators.ExecMethodResponseSentCallbackDecorator;
import org.khasanof.factories.response.decorators.ExecMethodResponseWrapFutureDecorator;
import org.khasanof.factories.response.methods.AbstractExecMethodResponse;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response
 * @since 1/14/2024 12:02 AM
 */
@SuppressWarnings({"rawtypes"})
public class DefaultExeMethodResponseAdapter implements ExecMethodResponseAdapter {

    private final Map<Class<? extends BotApiMethod>, ExecMethodResponse> methodResponseMap = new HashMap<>();
    private final List<ExecMethodResponse> responses = new ArrayList<>();

    @Override
    public Object createResponse(Method method, Object[] args) {
//        if (args.length == 1) {
//            Message message = new Message();
//            message.setMessageId(RandomUtils.nextInt());
//            message.setText(String.valueOf(args[0]));
//            message.setMessageThreadId(RandomUtils.nextInt());
//            return message;
//        }
        return responses.stream()
                .filter(execMethodResponse -> matchMethods(execMethodResponse.getMethod(), method))
                .map(execMethodResponse -> {
                    try {
                        return execMethodResponse.createResponse(method, args);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Match method not found!"));
//        throw new RuntimeException("Arguments greater than two!");
    }

    private boolean matchMethods(Method execMethod, Method method) {
        if (!Objects.equals(execMethod.getName(), method.getName())) {
            return false;
        }

        if (!Arrays.equals(execMethod.getParameterTypes(), method.getParameterTypes())) {
            return false;
        }
        return Objects.equals(execMethod.getModifiers(), method.getModifiers());
    }

    @Override
    public void setExecMethodResponseMap(Map<Class<? extends BotApiMethod>, ExecMethodResponse> methodResponseMap) {
        if (Objects.nonNull(methodResponseMap) && !methodResponseMap.isEmpty()) {
            this.methodResponseMap.putAll(methodResponseMap);
            this.responses.addAll(methodResponseMap.values());
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
