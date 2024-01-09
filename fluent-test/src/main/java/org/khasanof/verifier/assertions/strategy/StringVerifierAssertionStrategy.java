package org.khasanof.verifier.assertions.strategy;

import org.khasanof.memento.MethodInvokeMemento;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 12:07 AM
 */
public class StringVerifierAssertionStrategy implements VerifierAssertionStrategy<String> {

    @Override
    public boolean assertMatch(MethodInvokeMemento memento, String data) {
        return false;
    }

}
