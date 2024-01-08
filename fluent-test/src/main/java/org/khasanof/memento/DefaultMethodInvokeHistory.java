package org.khasanof.memento;

import java.util.Stack;

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
        return this.invokeMementos.firstElement();
    }

    @Override
    public Stack<MethodInvokeMemento> getHistory() {
        return this.invokeMementos;
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
