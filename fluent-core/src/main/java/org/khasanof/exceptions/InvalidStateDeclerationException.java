package org.khasanof.exceptions;

/**
 * @author Nurislom
 * @see org.khasanof.exceptions
 * @since 19.07.2023 12:17
 */
public class InvalidStateDeclerationException extends RuntimeException {

    public InvalidStateDeclerationException() {
    }

    public InvalidStateDeclerationException(String message) {
        super(message);
    }

    public InvalidStateDeclerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
