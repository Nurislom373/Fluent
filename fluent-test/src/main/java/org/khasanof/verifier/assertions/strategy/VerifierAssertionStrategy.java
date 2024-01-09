package org.khasanof.verifier.assertions.strategy;

import org.khasanof.memento.MethodInvokeMemento;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 12:06 AM
 */
public interface VerifierAssertionStrategy<T> {

    boolean assertMatch(MethodInvokeMemento memento, T data);

}
