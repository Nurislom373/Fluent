package org.khasanof.memento;

import java.util.Set;
import java.util.Stack;
import java.util.function.Predicate;

/**
 * @author Nurislom
 * @see org.khasanof.memento
 * @since 1/8/2024 10:21 PM
 */
public interface MethodInvokeHistory {

    MethodInvokeMemento getLastHistory();

    MethodInvokeMemento getFirstHistory();

    Stack<MethodInvokeMemento> getHistory();

    Set<MethodInvokeMemento> getHistoryWithPredicate(Predicate<MethodInvokeMemento> predicate);

    void addHistory(MethodInvokeMemento memento);

    void clearHistory();

}
