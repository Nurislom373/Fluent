package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.methods.HandleMessages;
import org.khasanof.config.ApplicationConstants;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 24.06.2023 1:00
 */
@Component
public class SimpleMessagesMatcher extends MultiGenericMatcher<HandleMessages, HandleMessage, String> {

    protected SimpleMessagesMatcher(GenericMatcher<HandleMessage, String> matcher) {
        super(matcher, ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleMessages annotation, String value) {
        return Arrays.stream(annotation.value())
                .anyMatch(handleMessage -> matcher.matcher(handleMessage, value));
    }

    @Override
    public Class<HandleMessages> getType() {
        return HandleMessages.class;
    }
}
