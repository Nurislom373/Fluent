package org.khasanof.executors.matcher;

import org.khasanof.annotation.exception.HandleException;
import org.khasanof.config.ApplicationConstants;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 04.07.2023 22:12
 */
@Component
public class SimpleExceptionMatcher extends GenericMatcher<HandleException, Object> {

    public SimpleExceptionMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleException annotation, Object value) {
        return Arrays.stream(annotation.value())
                .anyMatch(any -> any.isAssignableFrom(value.getClass()));
    }

    @Override
    public Class<HandleException> getType() {
        return HandleException.class;
    }
}
