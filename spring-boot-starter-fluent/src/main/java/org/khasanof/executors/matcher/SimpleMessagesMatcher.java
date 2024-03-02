package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.methods.HandleMessages;
import org.khasanof.models.matcher.RepeatableMatcherParameters;
import org.khasanof.service.expression.MultiExpressionMatcherService;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 24.06.2023 1:00
 */
@Component
public class SimpleMessagesMatcher extends MultiGenericMatcher<HandleMessages, HandleMessage, String> {

    protected SimpleMessagesMatcher(GenericMatcher<HandleMessage, String> matcher, MultiExpressionMatcherService multiExpressionMatcherService) {
        super(matcher, multiExpressionMatcherService);
    }

    @Override
    public boolean matcher(HandleMessages annotation, String value) {
        return expressionMatcherService.match(new RepeatableMatcherParameters(annotation.match(), annotation.value(), matcher, value));
    }
}
