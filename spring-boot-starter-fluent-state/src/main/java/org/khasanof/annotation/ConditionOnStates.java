package org.khasanof.annotation;

import org.khasanof.annotation.condition.Condition;
import org.khasanof.annotation.process.ProcessCondition;
import org.khasanof.feature.condition.FluentStateCondition;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 3/5/2024 10:18 PM
 */
@Documented
@ProcessCondition
@Retention(RetentionPolicy.RUNTIME)
@Condition(FluentStateCondition.class)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ConditionOnStates {

    /**
     *
     * @return
     */
    ConditionOnState[] value();
}
