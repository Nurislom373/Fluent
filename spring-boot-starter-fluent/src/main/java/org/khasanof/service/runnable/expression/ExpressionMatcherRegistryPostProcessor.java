package org.khasanof.service.runnable.expression;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.enums.MatchType;
import org.khasanof.executors.expression.SimpleExpressionMatcher;
import org.khasanof.executors.expression.SpelExpressionMatcher;
import org.khasanof.executors.expression.VariableExpressionMatcher;
import org.khasanof.registry.expression.ExpressionMatcherRegistry;
import org.khasanof.service.runnable.PostProcessor;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.service.runnable.expression
 * @since 2/24/2024 12:48 AM
 */
@Slf4j
@Service
public class ExpressionMatcherRegistryPostProcessor implements PostProcessor {

    private final ExpressionMatcherRegistry expressionMatcherRegistry;

    public ExpressionMatcherRegistryPostProcessor(ExpressionMatcherRegistry expressionMatcherRegistry) {
        this.expressionMatcherRegistry = expressionMatcherRegistry;
    }

    @Override
    public void run() {
        expressionMatcherRegistry.addExpressionMatcher(MatchType.EXPRESSION.name(), new SpelExpressionMatcher());
        expressionMatcherRegistry.addExpressionMatcher(MatchType.VAR_EXPRESSION.name(), new VariableExpressionMatcher());
    }
}
