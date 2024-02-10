package org.khasanof.verifier.assertions;

import org.khasanof.memento.MethodInvokeHistory;
import org.khasanof.memento.MethodInvokeMemento;
import org.khasanof.method.ExecuteMethodReflect;
import org.khasanof.verifier.assertions.VerifierAssertions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/8/2024 11:58 PM
 */
public class DefaultVerifierAssertions implements VerifierAssertions {

    private final MethodInvokeHistory methodInvokeHistory;
    private final ExecuteMethodReflect executeMethodReflect;

    public DefaultVerifierAssertions(MethodInvokeHistory methodInvokeHistory,
                                     ExecuteMethodReflect executeMethodReflect) {
        this.methodInvokeHistory = methodInvokeHistory;
        this.executeMethodReflect = executeMethodReflect;
    }

    @Override
    public VerifierAssertions expectSendMessage() {
        MethodInvokeMemento invokeMemento = methodInvokeHistory.getFirstHistory();
        Optional<SendMessage> matchArgument = getMatchArgument(invokeMemento.getArgs(), SendMessage.class);
        if (matchArgument.isEmpty()) {
            throw new AssertionError("expect message not found!");
        }
        return this;
    }

    @Override
    public VerifierAssertions expectSendMessage(String expectMessage) {
        MethodInvokeMemento invokeMemento = methodInvokeHistory.getFirstHistory();
        Set<Method> methods = executeMethodReflect.getMethodWithString();
        if (methods.contains(invokeMemento.getMethod())) {
            hasMessageInternal(invokeMemento, expectMessage);
        }
        return this;
    }

    @Override
    public VerifierAssertions expectSendMessageCount(long count) {
        Stack<MethodInvokeMemento> history = methodInvokeHistory.getHistory();
        if (!Objects.equals(history.size(), count)) {
            throw new AssertionError("expect message count not match!");
        }
        return this;
    }

    private void hasMessageInternal(MethodInvokeMemento memento, String expectMessage) {
        getMatchArgument(memento.getArgs(), String.class)
                .ifPresent(text -> {
                    if (!Objects.equals(text, expectMessage)) {
                        throw new AssertionError("expect message not match!");
                    }
                });
    }

    private <T> Optional<T> getMatchArgument(Object[] args, Class<T> clazz) {
        return Arrays.stream(args)
                .filter(arg -> Objects.equals(arg.getClass(), clazz) || clazz.isAssignableFrom(arg.getClass()))
                .map(arg -> (T) arg)
                .findFirst();
    }

}
