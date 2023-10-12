package org.khasanof.executors.expression.functions;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.AbstractFunction;
import com.ezylang.evalex.functions.FunctionParameter;
import com.ezylang.evalex.parser.Token;
import lombok.SneakyThrows;
import org.khasanof.enums.MemoryUnits;
import org.khasanof.utils.MemoryUtils;

import java.math.BigDecimal;

/**
 * @author Nurislom
 * @see org.khasanof.executors.expression.functions
 * @since 07.07.2023 23:28
 */
@FunctionParameter(name = "value")
@FunctionParameter(name = "begin")
@FunctionParameter(name = "end")
@FunctionParameter(name = "unit")
public class BetweenToUnitFunction extends AbstractFunction {

    @SneakyThrows
    @Override
    public EvaluationValue evaluate(Expression expression, Token functionToken, EvaluationValue... parameterValues) throws EvaluationException {
        MemoryUnits memoryUnits = MemoryUnits.valueOf(parameterValues[3].getStringValue());
        BigDecimal value = parameterValues[0].getNumberValue();
        BigDecimal begin = parameterValues[1].getNumberValue();
        BigDecimal end = parameterValues[2].getNumberValue();
        EvaluationValue evaluationValue = new Expression(MemoryUtils.getFormula(memoryUnits))
                .and("value", value)
                .evaluate();
        BigDecimal valueNumberValue = evaluationValue.getNumberValue();
        return new EvaluationValue(valueNumberValue.longValue() > begin.longValue() &&
                valueNumberValue.longValue() < end.longValue());
    }

}
