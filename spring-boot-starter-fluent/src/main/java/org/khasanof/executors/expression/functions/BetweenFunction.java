package org.khasanof.executors.expression.functions;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.AbstractFunction;
import com.ezylang.evalex.functions.FunctionParameter;
import com.ezylang.evalex.parser.Token;

import java.math.BigDecimal;

/**
 * @author Nurislom
 * @see org.khasanof.executors.expression.functions
 * @since 06.07.2023 23:44
 */
@FunctionParameter(name = "value")
@FunctionParameter(name = "begin")
@FunctionParameter(name = "end")
public class BetweenFunction extends AbstractFunction {

    @Override
    public EvaluationValue evaluate(Expression expression, Token functionToken, EvaluationValue... parameterValues) throws EvaluationException {
        BigDecimal value = parameterValues[0].getNumberValue();
        BigDecimal begin = parameterValues[1].getNumberValue();
        BigDecimal end = parameterValues[2].getNumberValue();
        return new EvaluationValue(value.longValue() > begin.longValue() && value.longValue() < end.longValue());
    }
}
