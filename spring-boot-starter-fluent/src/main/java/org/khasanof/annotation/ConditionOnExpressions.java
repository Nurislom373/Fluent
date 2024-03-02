package org.khasanof.annotation;

import org.khasanof.annotation.condition.Condition;
import org.khasanof.annotation.process.ProcessCondition;
import org.khasanof.feature.condition.FluentExpressionCondition;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 2/26/2024 9:53 PM
 */
@Documented
@ProcessCondition
@Retention(RetentionPolicy.RUNTIME)
@Condition(FluentExpressionCondition.class)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ConditionOnExpressions {

    /**
     *
     * @return
     */
    ConditionOnExpression[] value();
}
