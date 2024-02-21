package org.khasanof.annotation.expression;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation.expression
 * @since 30.07.2023 19:32
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BotVariable {

    String value();

}
