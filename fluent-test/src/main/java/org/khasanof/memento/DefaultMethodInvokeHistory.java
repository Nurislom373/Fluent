package org.khasanof.memento;

import java.util.Set;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.memento
 * @since 1/8/2024 10:24 PM
 */
public class DefaultMethodInvokeHistory implements MethodInvokeHistory {

    private final Stack<MethodInvokeMemento> invokeMementos = new Stack<>();

    @Override
    public MethodInvokeMemento getLastHistory() {
        return this.invokeMementos.lastElement();
    }

    @Override
    public MethodInvokeMemento getFirstHistory() {
        MethodInvokeMemento methodInvokeMemento = this.invokeMementos.firstElement();
        this.invokeMementos.remove(methodInvokeMemento);
        return methodInvokeMemento;
    }

    @Override
    public Stack<MethodInvokeMemento> getHistory() {
        return this.invokeMementos;
    }

    @Override
    public Set<MethodInvokeMemento> getHistoryWithPredicate(Predicate<MethodInvokeMemento> predicate) {
        return invokeMementos.stream()
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    @Override
    public void addHistory(MethodInvokeMemento memento) {
        this.invokeMementos.add(memento);
    }

    @Override
    public void clearHistory() {
        this.invokeMementos.clear();
    }
}
