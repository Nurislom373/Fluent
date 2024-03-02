package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.annotation.methods.HandleCallbacks;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 24.06.2023 0:57
 */
@Component
public class SimpleCallbacksMatcher extends GenericMatcher<HandleCallbacks, String> {

    private final GenericMatcher<HandleCallback, String> matcher;

    public SimpleCallbacksMatcher(GenericMatcher<HandleCallback, String> matcher, ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
        this.matcher = matcher;
    }

    @Override
    public boolean matcher(HandleCallbacks annotation, String value) {
        return Arrays.stream(annotation.value())
                .anyMatch(handleCallback -> matcher.matcher(handleCallback, value));
    }
}
