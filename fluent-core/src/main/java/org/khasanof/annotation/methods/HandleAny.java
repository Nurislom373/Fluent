package org.khasanof.annotation.methods;

import org.khasanof.enums.HandleType;
import org.khasanof.enums.Proceed;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see org.khasanof.annotation
 * @author <a href="https://github.com/Nurislom373">Nurislom</a>
 * @since 20.06.2023
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleAny {

    HandleType type() default HandleType.MESSAGE;

    Proceed proceed() default Proceed.PROCEED;

}
