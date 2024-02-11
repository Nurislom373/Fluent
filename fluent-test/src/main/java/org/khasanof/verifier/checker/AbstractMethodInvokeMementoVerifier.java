package org.khasanof.verifier.checker;

import org.khasanof.exceptions.InvalidParamsException;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.verifier.checker
 * @since 2/11/2024 8:05 PM
 */
public abstract class AbstractMethodInvokeMementoVerifier implements MethodInvokeMementoVerifier {

    protected void checkObjectEquals(Optional<?> optional, Object expect, String message) {
        optional.ifPresentOrElse(text -> {
            if (!Objects.equals(text, expect)) {
                throw new AssertionError(message);
            }
        }, () -> {
            throw new InvalidParamsException("Match argument not found!");
        });
    }
}
