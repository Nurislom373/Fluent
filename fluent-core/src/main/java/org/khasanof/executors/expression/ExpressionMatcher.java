package org.khasanof.executors.expression;

/**
 * @author Nurislom
 * @see org.khasanof.executors.expression
 * @since 01.08.2023 21:13
 */
public interface ExpressionMatcher<T> {

    boolean doMatch(String expression, T value);

    default boolean doMatch(String expression) {
        return false;
    }
}
