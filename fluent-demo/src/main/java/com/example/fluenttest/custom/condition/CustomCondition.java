package com.example.fluenttest.custom.condition;

import org.khasanof.annotation.condition.Condition;
import org.khasanof.annotation.process.ProcessCondition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nurislom
 * @see com.example.fluenttest.custom.condition
 * @since 2/29/2024 12:27 AM
 */
@ProcessCondition
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Condition(value = CustomFluentCondition.class)
public @interface CustomCondition {
}
