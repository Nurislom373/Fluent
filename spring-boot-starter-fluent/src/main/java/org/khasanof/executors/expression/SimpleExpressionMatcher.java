package org.khasanof.executors.expression;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.config.ExpressionConfiguration;
import com.ezylang.evalex.operators.OperatorIfc;
import com.ezylang.evalex.parser.ParseException;
import org.khasanof.executors.expression.functions.*;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.executors.expression
 * @since 02.07.2023 19:18
 */
public class SimpleExpressionMatcher implements ExpressionMatcher<Object> {

    private final ExpressionConfiguration configuration = ExpressionConfiguration.builder()
            .mathContext(ExpressionConfiguration.DEFAULT_MATH_CONTEXT)
            .powerOfPrecedence(OperatorIfc.OPERATOR_PRECEDENCE_POWER)
            .decimalPlacesRounding(ExpressionConfiguration.DECIMAL_PLACES_ROUNDING_UNLIMITED)
            .build();

    {
        configuration.withAdditionalFunctions(
                Map.entry("END_WITH", new EndWithFunction()),
                Map.entry("START_WITH", new StartWithFunction()),
                Map.entry("EQUALS", new EqualsFunction()),
                Map.entry("CONTAINS", new ContainsFunction()),
                Map.entry("BETWEEN", new BetweenFunction()),
                Map.entry("BETWEEN_UNIT", new BetweenToUnitFunction())
        );
    }

    @Override
    public boolean doMatch(String expression, Object value) {
        try {
            return tryDoMatch(expression, value);
        } catch (EvaluationException | ParseException e) {
            return false;
        }
    }

    private Boolean tryDoMatch(String expression, Object value) throws EvaluationException, ParseException {
        return new Expression(expression.replaceAll("'", "\""), configuration)
                .and("value", value)
                .evaluate()
                .getBooleanValue();
    }
}
