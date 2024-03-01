package org.khasanof.annotation.methods;

import org.khasanof.annotation.process.ProcessUpdate;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation.methods
 * @since 3/1/2024 9:51 PM
 */
@Documented
@ProcessUpdate
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleUnknown {
}
