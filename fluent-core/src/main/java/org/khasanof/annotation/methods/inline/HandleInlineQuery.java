package org.khasanof.annotation.methods.inline;

import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.MatchScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nurislom
 * @see org.khasanof.annotation.methods.inline
 * @since 29.07.2023 0:43
 */
@ProcessUpdate
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleInlineQuery {

    String[] value() default {};

    MatchScope match() default MatchScope.EQUALS;

}
