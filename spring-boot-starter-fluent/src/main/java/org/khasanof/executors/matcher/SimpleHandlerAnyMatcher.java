package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.enums.HandleType;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 25.06.2023 22:50
 */
@Component
public class SimpleHandlerAnyMatcher extends GenericMatcher<HandleAny, HandleType> {

    public SimpleHandlerAnyMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
    }

    @Override
    public boolean matcher(HandleAny annotation, HandleType value) {
        return Arrays.asList(annotation.type()).contains(value);
    }
}
