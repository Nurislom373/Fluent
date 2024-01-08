package org.khasanof;

import org.khasanof.memento.MethodInvokeHistory;
import org.khasanof.memento.MethodInvokeMemento;
import org.khasanof.method.ExecuteMethodReflect;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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
    public VerifierAssertions hasMessage(String expectMessage) {
        MethodInvokeMemento invokeMemento = methodInvokeHistory.getFirstHistory();
        Set<Method> methods = executeMethodReflect.getMethodWithString();
        if (methods.contains(invokeMemento.getMethod())) {
            hasMessageInternal(invokeMemento, expectMessage);
        }
        return this;
    }

    private void hasMessageInternal(MethodInvokeMemento memento, String expectMessage) {
        getMatchArgument(memento.getArgs(), SendMessage.class)
                .ifPresent(sendMessage -> {
                    if (!Objects.equals(sendMessage.getText(), expectMessage)) {
                        throw new AssertionError("expect message not match!");
                    }
                });
    }

    private <T> Optional<T> getMatchArgument(Object[] args, Class<T> clazz) {
        return Arrays.stream(args)
                .filter(arg -> Objects.equals(arg.getClass(), clazz) || clazz.isAssignableFrom(arg.getClass()))
                .map(arg -> (T) args)
                .findFirst();
    }

}
