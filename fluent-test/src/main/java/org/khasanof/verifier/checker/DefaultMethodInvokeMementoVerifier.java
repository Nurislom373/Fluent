package org.khasanof.verifier.checker;

import org.khasanof.memento.MethodInvokeMemento;

import static org.khasanof.utils.FluentTestUtils.getMatchArgument;

/**
 * @author Nurislom
 * @see org.khasanof.verifier.assertions
 * @since 2/11/2024 7:54 PM
 */
public class DefaultMethodInvokeMementoVerifier extends AbstractMethodInvokeMementoVerifier {

    @Override
    public void expectMessage(MethodInvokeMemento memento, String exceptText) {
        checkObjectEquals(getMatchArgument(memento.getArgs(), 0, String.class), exceptText, "expect message not match!");
    }

    @Override
    public void expectAnswerCallbackQuery(MethodInvokeMemento memento, String callbackData) {
        checkObjectEquals(getMatchArgument(memento.getArgs(), 0, String.class), callbackData, "expect answer callback query not match!");
    }
}
