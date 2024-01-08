package org.khasanof;

import org.khasanof.memento.MethodInvokeMemento;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 12:07 AM
 */
public class StringVerifierAssertions implements InternalVerifierAssertions<String> {

    @Override
    public boolean assertMatch(MethodInvokeMemento memento, String data) {
        return false;
    }

}
