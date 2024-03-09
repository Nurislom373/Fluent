package org.khasanof.inline.matcher;

import org.khasanof.annotation.HandleChosenInlineQuery;
import org.khasanof.enums.ChosenInlineQueryProperty;
import org.khasanof.executors.matcher.GenericMatcher;
import org.khasanof.models.matcher.MatcherParameters;
import org.khasanof.models.matcher.MatcherProperty;
import org.khasanof.models.matcher.function.PropertyFunction;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.inline.matcher
 * @since 3/9/2024 12:41 PM
 */
@Component
public class ChosenInlineQueryMatcher extends GenericMatcher<HandleChosenInlineQuery, ChosenInlineQuery> {

    private final Set<MatcherProperty> matcherProperties = new HashSet<>();

    public ChosenInlineQueryMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
        fillMatcherProperties();
    }

    @Override
    public boolean matcher(HandleChosenInlineQuery annotation, ChosenInlineQuery value) {
        return expressionMatcherService.match(createParameters(annotation, value));
    }

    private MatcherParameters createParameters(HandleChosenInlineQuery annotation, ChosenInlineQuery value) {
        return new MatcherParameters(value, annotation.property(), annotation.match(), annotation.value(), matcherProperties);
    }

    void fillMatcherProperties() {
        matcherProperties.add(new MatcherProperty(ChosenInlineQueryProperty.QUERY, (PropertyFunction<ChosenInlineQuery>) ChosenInlineQuery::getQuery));
        matcherProperties.add(new MatcherProperty(ChosenInlineQueryProperty.RESULT_ID, (PropertyFunction<ChosenInlineQuery>) ChosenInlineQuery::getResultId));
    }
}
