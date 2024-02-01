package org.khasanof.exceptions;

/**
 * @author Nurislom
 * @see org.khasanof.exceptions
 * @since 1/23/2024 9:48 PM
 */
public class UnknownUpdateTypeException extends RuntimeException {

    public UnknownUpdateTypeException() {
    }

    public UnknownUpdateTypeException(String message) {
        super(message);
    }

    public UnknownUpdateTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownUpdateTypeException(Throwable cause) {
        super(cause);
    }

    public UnknownUpdateTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
