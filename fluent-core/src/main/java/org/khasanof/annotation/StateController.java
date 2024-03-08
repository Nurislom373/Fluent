package org.khasanof.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.EnumSet;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 09.07.2023 18:40
 */
@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StateController {
}
