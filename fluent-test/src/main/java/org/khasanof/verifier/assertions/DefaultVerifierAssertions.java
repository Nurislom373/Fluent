package org.khasanof.verifier.assertions;

import org.khasanof.memento.MethodInvokeHistory;
import org.khasanof.memento.MethodInvokeMemento;
import org.khasanof.method.ExecuteMethodReflect;
import org.khasanof.service.template.operations.SendTextOperations;
import org.khasanof.service.template.operations.query.AnswerCallbackQueryOperations;
import org.khasanof.verifier.checker.MethodInvokeMementoVerifier;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

import static org.khasanof.utils.FluentTestUtils.isMatchMethod;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/8/2024 11:58 PM
 */
public class DefaultVerifierAssertions implements VerifierAssertions {

    private final MethodInvokeHistory methodInvokeHistory;
    private final ExecuteMethodReflect executeMethodReflect;
    private final MethodInvokeMementoVerifier methodInvokeMementoVerifier;

    public DefaultVerifierAssertions(MethodInvokeHistory methodInvokeHistory,
                                     ExecuteMethodReflect executeMethodReflect,
                                     MethodInvokeMementoVerifier methodInvokeMementoVerifier) {

        this.methodInvokeHistory = methodInvokeHistory;
        this.executeMethodReflect = executeMethodReflect;
        this.methodInvokeMementoVerifier = methodInvokeMementoVerifier;
    }

    @Override
    public VerifierAssertions expectSendText() {
        return expectLatestMethod(SendTextOperations.class, "expect message not found!");
    }

    @Override
    public VerifierAssertions expectSendText(String expectMessage) {
        MethodInvokeMemento invokeMemento = methodInvokeHistory.getFirstHistory();
        Set<Method> methods = executeMethodReflect.getPublicMethods(SendTextOperations.class);

        if (methods.contains(invokeMemento.getMethod())) {
            methodInvokeMementoVerifier.expectMessage(invokeMemento, expectMessage);
        }
        return this;
    }

    @Override
    public VerifierAssertions expectSendTextCount(long count) {
        return expectSendCount(count, SendTextOperations.class, "expect message count not match!");
    }

    @Override
    public VerifierAssertions expectSendAnswerCallbackQuery() {
        return expectLatestMethod(AnswerCallbackQueryOperations.class, "expect answer callback query not found!");
    }

    @Override
    public VerifierAssertions expectSendAnswerCallbackQuery(String callbackData) {
        MethodInvokeMemento invokeMemento = methodInvokeHistory.getFirstHistory();
        Set<Method> methods = executeMethodReflect.getPublicMethods(AnswerCallbackQueryOperations.class);

        if (methods.contains(invokeMemento.getMethod())) {
            methodInvokeMementoVerifier.expectAnswerCallbackQuery(invokeMemento, callbackData);
        }
        return this;
    }

    @Override
    public VerifierAssertions expectSendAnswerCallbackQueryCount(long count) {
        return expectSendCount(count, AnswerCallbackQueryOperations.class, "expect message count not match!");
    }

    private VerifierAssertions expectLatestMethod(Class<?> expectMethodClass, String message) {
        MethodInvokeMemento invokeMemento = methodInvokeHistory.getFirstHistory();
        Set<Method> publicMethods = executeMethodReflect.getPublicMethods(expectMethodClass);

        if (!isMatchMethod(invokeMemento.getMethod(), publicMethods)) {
            throw new AssertionError(message);
        }
        return this;
    }

    private VerifierAssertions expectSendCount(long count, Class<?> expectMethodClass, String message) {
        Set<Method> publicMethods = executeMethodReflect.getPublicMethods(expectMethodClass);
        Set<MethodInvokeMemento> mementos = getHistory(publicMethods);

        if (!Objects.equals(mementos.size(), count)) {
            throw new AssertionError(message);
        }
        return this;
    }

    private Set<MethodInvokeMemento> getHistory(Set<Method> publicMethods) {
        return methodInvokeHistory.getHistoryWithPredicate(memento -> isMatchMethod(memento.getMethod(), publicMethods));
    }
}
