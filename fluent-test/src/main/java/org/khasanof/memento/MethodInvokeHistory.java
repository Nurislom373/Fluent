package org.khasanof.memento;

import java.util.Stack;

/**
 * @author Nurislom
 * @see org.khasanof.memento
 * @since 1/8/2024 10:21 PM
 */
public interface MethodInvokeHistory {

    MethodInvokeMemento getLastHistory();

    MethodInvokeMemento getFirstHistory();

    Stack<MethodInvokeMemento> getHistory();

    void addHistory(MethodInvokeMemento memento);

    void clearHistory();

}
