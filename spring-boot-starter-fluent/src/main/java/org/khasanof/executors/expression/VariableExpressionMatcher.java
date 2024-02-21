package org.khasanof.executors.expression;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.khasanof.tokenizer.StringTokenizerUtils.getTokenWithList;

/**
 * @author Nurislom
 * @see org.khasanof.executors.expression
 * @since 30.07.2023 20:59
 */
@Component
public class VariableExpressionMatcher implements ExpressionMatcher<String>, ExpressionVariables {

    @Override
    public boolean doMatch(String expression, String value) {
        List<String> expList = getTokenWithList(expression, " ");
        List<String> valList = getTokenWithList(value, " ");
        return expValListsMatcher(expList, valList).getKey();
    }

    @Override
    public Map<String, String> getMatchVariables(String expression, String value) {
        List<String> expList = getTokenWithList(expression, " ");
        List<String> valList = getTokenWithList(value, " ");
        Map.Entry<Boolean, Map<String, String>> booleanMapEntry = expValListsMatcher(expList, valList);
        if (booleanMapEntry.getKey()) {
            return booleanMapEntry.getValue();
        }
        return new HashMap<>();
    }

    @Override
    public boolean isExpression(String expression) {
        List<String> list = getTokenWithList(expression.strip(), "{.}");
        List<String> strings = list.stream().filter(f -> f.contains(":") &&
                isValidRegex(getRegex(f))).toList();
        return strings.isEmpty();
    }

    @Override
    public String[] getExpression(String expression) {
        return new String[0];
    }

    private String getRegex(String var) {
        return var.substring(var.indexOf(":") + 1, var.length() - 1);
    }

    private String getVarName(String var) {
        return var.substring(1, var.indexOf(":"));
    }

    private Map.Entry<Boolean, Map<String, String>> expValListsMatcher(List<String> expList, List<String> valList) {
        Map<String, String> variables = new HashMap<>();
        boolean hasTwoListNext = true, allMatch = true;
        int expCount = 0, valCount = 0;
        while (hasTwoListNext) {
            if (expList.size() != valList.size()) {
                allMatch = false;
                break;
            }
            String varExp = null, varVal = null;
            if (expList.size() > expCount) {
                varExp = expList.get(expCount);
                expCount++;
            }
            if (valList.size() > valCount) {
                varVal = valList.get(valCount);
                valCount++;
            }
            if (expCount == expList.size() && valCount == valList.size()) {
                if (Objects.isNull(varExp) && Objects.isNull(varVal)) {
                    break;
                }
            } else if ((Objects.isNull(varExp) || varExp.isBlank()) || (Objects.isNull(varVal) || varVal.isBlank())) {
                allMatch = false;
                break;
            }
            if (strStartAndMatch(varExp)) {
                boolean matches = Pattern.compile(getRegex(varExp)).matcher(varVal).find();
                if (!matches) {
                    hasTwoListNext = false;
                    allMatch = false;
                } else {
                    variables.put(getVarName(varExp), varVal);
                }
            } else {
                if (!varExp.equals(varVal)) {
                    hasTwoListNext = false;
                    allMatch = false;
                }
            }
        }
        return Map.entry(allMatch, variables);
    }

    private boolean strStartAndMatch(String var) {
        return Objects.nonNull(var) && var.startsWith("{") && var.endsWith("}");
    }

    private boolean isValidRegex(String regex) {
        try {
            Pattern.compile(regex);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
