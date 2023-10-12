package org.khasanof.exceptions;

/**
 * @author Nurislom
 * @see org.khasanof.exceptions
 * @since 04.07.2023 23:09
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
