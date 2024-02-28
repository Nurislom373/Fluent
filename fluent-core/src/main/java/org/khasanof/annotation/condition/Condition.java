package org.khasanof.annotation.condition;

import org.khasanof.annotation.process.ProcessCondition;
import org.khasanof.feature.condition.FluentCondition;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation.condition
 * @since 2/26/2024 10:40 PM
 */
@Documented
@ProcessCondition
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Condition {

    /**
     *
     * @return
     */
    Class<? extends FluentCondition> value();
}
