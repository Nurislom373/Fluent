package org.khasanof.registry.expression;

import org.khasanof.executors.expression.ExpressionMatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.registry.expression
 * @since 2/24/2024 12:35 AM
 */
public class DefaultExpressionMatcherRegistry implements ExpressionMatcherRegistry {

    private final Map<String, ExpressionMatcher> expressionMatcherMap = new HashMap<>();

    @Override
    public ExpressionMatcher getKey(String key) {
        return this.expressionMatcherMap.get(key);
    }

    @Override
    public Map<String, ExpressionMatcher> getExpressionMatchers() {
        return this.expressionMatcherMap;
    }

    @Override
    public void addExpressionMatchers(Map<String, ExpressionMatcher> expressionMatchers) {
        if (Objects.nonNull(expressionMatchers)) {
            this.expressionMatcherMap.putAll(expressionMatchers);
        }
    }

    @Override
    public void addExpressionMatcher(String key, ExpressionMatcher expressionMatcher) {
        if (Objects.nonNull(expressionMatcher)) {
            this.expressionMatcherMap.put(key, expressionMatcher);
        }
    }
}
