package org.khasanof.annotation;

import org.khasanof.enums.ChosenInlineQueryProperty;
import org.khasanof.enums.MatchType;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 3/9/2024 12:25 PM
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleChosenInlineQuery {

    String value() default "[\\s\\S]*"; // any text regex

    MatchType match() default MatchType.REGEX;

    ChosenInlineQueryProperty property() default ChosenInlineQueryProperty.QUERY;
}
