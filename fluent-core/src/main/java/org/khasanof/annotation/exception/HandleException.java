package org.khasanof.annotation.exception;

import java.lang.annotation.*;

/**
 * Annotation for handling exceptions in specific handler classes
 *
 * @author Nurislom
 * @see org.khasanof.annotation.exception
 * @since 04.07.2023 21:33
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleException {

    Class<? extends Throwable>[] value();

}
