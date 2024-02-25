package org.khasanof.executors.expression;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.expression
 * @since 2/22/2024 12:08 AM
 */
public class SpelExpressionMatcher implements ExpressionMatcher<Object> {

    private final ExpressionParser parser = new SpelExpressionParser();

    @Override
    public boolean doMatch(String expression, Object value) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("value", value);
        return Objects.equals(executeExpression(expression, context), Boolean.TRUE);
    }

    private Boolean executeExpression(String expression, StandardEvaluationContext context) {
        return parser.parseExpression(expression).getValue(context, Boolean.class);
    }
}
