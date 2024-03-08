package org.khasanof.verifier.checker;

import org.khasanof.memento.MethodInvokeMemento;

/**
 * @author Nurislom
 * @see org.khasanof.verifier.assertions
 * @since 2/11/2024 7:43 PM
 */
public interface MethodInvokeMementoVerifier {

    void expectMessage(MethodInvokeMemento memento, String exceptText);

    void expectAnswerCallbackQuery(MethodInvokeMemento memento, String callbackData);

}
