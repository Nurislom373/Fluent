package org.khasanof.annotation;

import org.khasanof.annotation.condition.Condition;
import org.khasanof.annotation.process.ProcessCondition;
import org.khasanof.feature.expression.FluentExpressionCondition;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 2/26/2024 9:48 PM
 */
@Documented
@ProcessCondition
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ConditionOnExpressions.class)
@Condition(FluentExpressionCondition.class)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ConditionOnExpression {

    /**
     *
     * @return
     */
    String value();
}
