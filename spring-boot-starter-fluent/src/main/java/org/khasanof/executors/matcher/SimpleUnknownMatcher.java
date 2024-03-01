package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleUnknown;
import org.khasanof.executors.expression.ExpressionMatcher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 3/1/2024 10:03 PM
 */
@Component
public class SimpleUnknownMatcher extends GenericMatcher<HandleUnknown, Update> {

    public SimpleUnknownMatcher(Map<String, ExpressionMatcher> expressionMatcherMap) {
        super(expressionMatcherMap);
    }

    @Override
    public boolean matcher(HandleUnknown annotation, Update value) {
        return true; //
    }
}
