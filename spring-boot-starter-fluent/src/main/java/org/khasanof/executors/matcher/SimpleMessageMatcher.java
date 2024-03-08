package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 25.06.2023 22:50
 */
@Component
public class SimpleMessageMatcher extends GenericMatcher<HandleMessage, String> {

    public SimpleMessageMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
    }

    @Override
    public boolean matcher(HandleMessage handleMessage, String value) {
        return expressionMatcherService.match(handleMessage.match(), handleMessage.value(), value);
    }
}
