package org.khasanof.annotation.methods;

import org.khasanof.annotation.process.ProcessUpdate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nurislom
 * @see org.khasanof.annotation.methods
 * @since 2/1/2024 9:33 PM
 */
@ProcessUpdate
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleWebAppData {
}
