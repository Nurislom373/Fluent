package org.khasanof.executors.matcher;

import org.khasanof.enums.MatchType;
import org.khasanof.executors.expression.ExpressionMatcher;
import org.khasanof.executors.expression.ExpressionMatcherAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

import static org.khasanof.executors.matcher.MatcherConstants.*;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 03.07.2023 17:47
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractMatcher {

    protected final Map<String, ExpressionMatcher> expressionMatcherMap;

    protected final Map<Map.Entry<MatchType, Class>,
            BiFunction<Object, Object, Boolean>> matchFunctions = new HashMap<>();

    {
        setUp();
    }

    public AbstractMatcher(Map<String, ExpressionMatcher> expressionMatcherMap) {
        this.expressionMatcherMap = expressionMatcherMap;
    }

    private void setUp() {
        matchFunctions.put(Map.entry(MatchType.EQUALS, Integer.class), (var1, var2) -> (int) var1 == (int) var2);

        matchFunctions.put(Map.entry(MatchType.EQUALS, String.class), (var1, var2) -> String.valueOf(var1).equals(String.valueOf(var2)));
        matchFunctions.put(Map.entry(MatchType.EQUALS_IGNORE_CASE, String.class),
                (var1, var2) -> String.valueOf(var1).equalsIgnoreCase(String.valueOf(var2)));
        matchFunctions.put(Map.entry(MatchType.END_WITH, String.class), (var1, var2) -> String.valueOf(var2)
                .endsWith(String.valueOf(var1)));
        matchFunctions.put(Map.entry(MatchType.START_WITH, String.class), (var1, var2) -> String.valueOf(var2)
                .startsWith(String.valueOf(var1)));
        matchFunctions.put(Map.entry(MatchType.CONTAINS, String.class), (var1, var2) -> String.valueOf(var2)
                .contains(String.valueOf(var1)));
        matchFunctions.put(Map.entry(MatchType.REGEX, Object.class), (var1, var2) ->
                Pattern.compile(String.valueOf(var1)).matcher(String.valueOf(var2)).find());
        matchFunctions.put(Map.entry(MatchType.EXPRESSION, Object.class), (var1, var2) ->
                ExpressionMatcherAdapter.doMatch(expressionMatcherMap.get(EXPRESSION), String.valueOf(var1), var2));
        matchFunctions.put(Map.entry(MatchType.VAR_EXPRESSION, String.class), (var1, var2) ->
                ExpressionMatcherAdapter.doMatch(expressionMatcherMap.get(VAR_EXPRESSION), String.valueOf(var1), String.valueOf(var2)));
    }

    protected Class getMatchType(Object type, MatchType scope) {
        if (scope.equals(MatchType.EXPRESSION) || scope.equals(MatchType.REGEX)) {
            return Object.class;
        }
        return type.getClass();
    }
}
