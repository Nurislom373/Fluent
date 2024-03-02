package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.06.2023
 * <br/>
 * Time: 21:55
 * <br/>
 * Package: org.khasanof.core.executors.matcher
 */
@Component
public class SimpleCallbackMatcher extends GenericMatcher<HandleCallback, String> {

    public SimpleCallbackMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
    }

    @Override
    public boolean matcher(HandleCallback handleCallback, String value) {
        return Arrays.stream(handleCallback.value())
                .anyMatch(annValue -> expressionMatcherService.match(handleCallback.match(), annValue, value));
    }
}
