package org.khasanof.annotation;

import org.khasanof.enums.MatchType;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 3/3/2024 5:28 PM
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleInlineQuery {

    String value() default "[\\s\\S]*"; // any text regex

    MatchType match() default MatchType.REGEX;
}
