package org.khasanof.executors.expression.functions;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.AbstractFunction;
import com.ezylang.evalex.functions.FunctionParameter;
import com.ezylang.evalex.parser.Token;

/**
 * @author Nurislom
 * @see org.khasanof.executors.expression
 * @since 02.07.2023 20:01
 */
@FunctionParameter(name = "value")
@FunctionParameter(name = "const")
public class StartWithFunction extends AbstractFunction {

    @Override
    public EvaluationValue evaluate(Expression expression, Token functionToken, EvaluationValue... parameterValues) throws EvaluationException {
        return new EvaluationValue(parameterValues[1].getStringValue().startsWith(parameterValues[0].getStringValue()));
    }
}
