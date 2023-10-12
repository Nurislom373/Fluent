package org.khasanof.config;

import org.khasanof.executors.expression.ExpressionMatcher;
import org.khasanof.executors.expression.SimpleExpressionMatcher;
import org.khasanof.executors.expression.VariableExpressionMatcher;
import org.khasanof.executors.matcher.MatcherConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 8/18/2023 6:24 AM
 */
public class ApplicationConstants {

    public static final String STATE_PROCESSOR_EXPRESSION = "${fluent.bot.process-type} != 'update'";

    public static final Map<String, ExpressionMatcher> MATCHER_MAP = new HashMap<>(){{
        put(MatcherConstants.EXPRESSION, new SimpleExpressionMatcher());
        put(MatcherConstants.VAR_EXPRESSION, new VariableExpressionMatcher());
    }};

}
