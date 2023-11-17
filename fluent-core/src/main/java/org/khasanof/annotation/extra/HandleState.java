package org.khasanof.annotation.extra;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nurislom
 * @see org.khasanof.annotation.extra
 * @since 09.07.2023 18:50
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleState {
}
