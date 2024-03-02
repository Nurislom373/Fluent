package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleUnknown;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 3/1/2024 10:03 PM
 */
@Component
public class SimpleUnknownMatcher extends GenericMatcher<HandleUnknown, Update> {

    public SimpleUnknownMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
    }

    @Override
    public boolean matcher(HandleUnknown annotation, Update value) {
        return true;
    }
}
