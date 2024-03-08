package org.khasanof.inline.matcher;

import org.khasanof.annotation.HandleInlineQuery;
import org.khasanof.executors.matcher.GenericMatcher;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;

/**
 * @author Nurislom
 * @see org.khasanof.inline.matcher
 * @since 3/3/2024 5:41 PM
 */
@Component
public class InlineQueryMatcher extends GenericMatcher<HandleInlineQuery, InlineQuery> {

    public InlineQueryMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
    }

    @Override
    public boolean matcher(HandleInlineQuery annotation, InlineQuery value) {
        return expressionMatcherService.match(annotation.match(), annotation.value(), value.getQuery());
    }
}
