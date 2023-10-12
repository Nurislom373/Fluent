package org.khasanof.executors.expression;

/**
 * @author Nurislom
 * @see org.khasanof.executors.expression
 * @since 01.08.2023 21:20
 */
public abstract class ExpressionMatcherAdapter {

    public static <T> boolean doMatch(ExpressionMatcher<T> expressionMatcher, String exp, T value) {
        return expressionMatcher.doMatch(exp, value);
    }

    public static boolean doMatch(ExpressionMatcher<?> expressionMatcher, String exp) {
        return expressionMatcher.doMatch(exp);
    }

}
