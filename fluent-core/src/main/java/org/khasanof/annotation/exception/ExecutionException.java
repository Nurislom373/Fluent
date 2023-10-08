package org.khasanof.annotation.exception;

import java.lang.annotation.*;

/**
 * this annotation is used to display a method exception.
 *
 * @author Nurislom
 * @see org.khasanof.annotation.extra
 * @since 8/13/2023 9:39 PM
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutionException {
}
