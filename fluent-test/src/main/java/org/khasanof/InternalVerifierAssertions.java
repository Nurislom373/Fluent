package org.khasanof;

import org.khasanof.memento.MethodInvokeMemento;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 12:06 AM
 */
public interface InternalVerifierAssertions<T> {

    boolean assertMatch(MethodInvokeMemento memento, T data);

}
