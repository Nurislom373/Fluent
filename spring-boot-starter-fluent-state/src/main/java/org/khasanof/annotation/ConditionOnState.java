package org.khasanof.annotation;

import org.khasanof.annotation.condition.Condition;
import org.khasanof.annotation.process.ProcessCondition;
import org.khasanof.feature.condition.FluentStateCondition;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 3/5/2024 10:17 PM
 */
@Documented
@ProcessCondition
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ConditionOnStates.class)
@Condition(FluentStateCondition.class)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ConditionOnState {

    /**
     *
     * @return
     */
    String[] value();

    /**
     *
     * @return
     */
    boolean nonMatch() default false;
}
