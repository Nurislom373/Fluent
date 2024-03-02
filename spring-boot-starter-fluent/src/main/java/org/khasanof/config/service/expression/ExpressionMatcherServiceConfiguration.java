package org.khasanof.config.service.expression;

import org.khasanof.enums.MatchType;
import org.khasanof.models.matcher.function.MatcherFunction;
import org.khasanof.models.matcher.MatcherModel;
import org.khasanof.registry.expression.DefaultExpressionMatcherRegistry;
import org.khasanof.registry.expression.ExpressionMatcherRegistry;
import org.khasanof.service.expression.DefaultExpressionMatcherService;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Nurislom
 * @see org.khasanof.config.service.expression
 * @since 2/24/2024 12:21 AM
 */
@Configuration
public class ExpressionMatcherServiceConfiguration {

    /**
     *
     * @return {@link ExpressionMatcherRegistry} bean
     */
    @Bean
    public ExpressionMatcherRegistry expressionMatcherRegistry() {
        return new DefaultExpressionMatcherRegistry();
    }

    /**
     *
     * @return {@link ExpressionMatcherService} bean
     */
    @Bean
    public ExpressionMatcherService expressionMatcherService(ExpressionMatcherRegistry matcherRegistry) {
        DefaultExpressionMatcherService expressionMatcherService = new DefaultExpressionMatcherService();
        expressionMatcherService.addMatchers(getDefaultMatcherModels(matcherRegistry));
        return expressionMatcherService;
    }

    private Set<MatcherModel> getDefaultMatcherModels(ExpressionMatcherRegistry matcherRegistry) {
        return Set.of(
                new MatcherModel(MatchType.EQUALS, Integer.class, getIntEqualsMatcherFunction()),

                new MatcherModel(MatchType.EQUALS, String.class, getEqualsMatcherFunction()),

                new MatcherModel(MatchType.EQUALS_IGNORE_CASE, String.class, getEqualsIgnoreCaseMatcherFunction()),

                new MatcherModel(MatchType.END_WITH, String.class, getEndWithMatcherFunction()),

                new MatcherModel(MatchType.START_WITH, String.class, getStartWithMatcherFunction()),

                new MatcherModel(MatchType.CONTAINS, String.class, getContainsMatcherFunction()),

                new MatcherModel(MatchType.REGEX, Object.class, getRegexMatcherFunction()),

                new MatcherModel(MatchType.EXPRESSION, Object.class, getExpressionMatcherFunction(matcherRegistry)),

                new MatcherModel(MatchType.VAR_EXPRESSION, String.class, getVarExpressionMatcherFunction(matcherRegistry))
        );
    }

    private MatcherFunction getIntEqualsMatcherFunction() {
        return (var1, var2) -> (int) var1 == (int) var2;
    }

    private MatcherFunction getEqualsMatcherFunction() {
        return (var1, var2) -> String.valueOf(var1).equals(String.valueOf(var2));
    }

    private MatcherFunction getEqualsIgnoreCaseMatcherFunction() {
        return (var1, var2) -> String.valueOf(var1).equalsIgnoreCase(String.valueOf(var2));
    }

    private MatcherFunction getEndWithMatcherFunction() {
        return (var1, var2) -> String.valueOf(var2).endsWith(String.valueOf(var1));
    }

    private MatcherFunction getStartWithMatcherFunction() {
        return (var1, var2) -> String.valueOf(var2).startsWith(String.valueOf(var1));
    }

    private MatcherFunction getContainsMatcherFunction() {
        return (var1, var2) -> String.valueOf(var2).contains(String.valueOf(var1));
    }

    private MatcherFunction getRegexMatcherFunction() {
        return (var1, var2) -> Pattern.compile(String.valueOf(var1)).matcher(String.valueOf(var2)).find();
    }

    private MatcherFunction getExpressionMatcherFunction(ExpressionMatcherRegistry matcherRegistry) {
        return (var1, var2) -> matcherRegistry.getKey(MatchType.EXPRESSION.name()).doMatch(String.valueOf(var1), var2);
    }

    private MatcherFunction getVarExpressionMatcherFunction(ExpressionMatcherRegistry matcherRegistry) {
        return (var1, var2) -> matcherRegistry.getKey(MatchType.VAR_EXPRESSION.name()).doMatch(String.valueOf(var1), String.valueOf(var2));
    }
}
