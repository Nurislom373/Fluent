package org.khasanof.registry.expression;

import org.khasanof.executors.expression.ExpressionMatcher;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.registry.expression
 * @since 2/22/2024 12:13 AM
 */
public interface ExpressionMatcherRegistry {

    /**
     *
     * @param key
     * @return
     */
    ExpressionMatcher getKey(String key);

    /**
     *
     * @return
     */
    Map<String, ExpressionMatcher> getExpressionMatchers();

    /**
     *
     * @param expressionMatchers
     */
    void addExpressionMatchers(Map<String, ExpressionMatcher> expressionMatchers);

    /**
     *
     * @param expressionMatcher
     */
    void addExpressionMatcher(String key, ExpressionMatcher expressionMatcher);
}
