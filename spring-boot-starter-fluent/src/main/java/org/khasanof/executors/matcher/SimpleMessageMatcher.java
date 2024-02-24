package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.config.ApplicationConstants;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 25.06.2023 22:50
 */
@Component
public class SimpleMessageMatcher extends GenericMatcher<HandleMessage, String> {

    private final ExpressionMatcherService expressionMatcherService;

    public SimpleMessageMatcher(ExpressionMatcherService expressionMatcherService) {
        super(ApplicationConstants.MATCHER_MAP);
        this.expressionMatcherService = expressionMatcherService;
    }

    @Override
    public boolean matcher(HandleMessage handleMessage, String value) {
//        return matchFunctions.get(Map.entry(handleMessage.match(), getMatchType(value, handleMessage.match())))
//                .apply(handleMessage.value(), value);
        return expressionMatcherService.match(handleMessage.match(), handleMessage.value(), value);
    }

    @Override
    public Class<HandleMessage> getType() {
        return HandleMessage.class;
    }

}
