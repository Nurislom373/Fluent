package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.config.ApplicationConstants;
import org.khasanof.enums.HandleType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 25.06.2023 22:50
 */
@Component
public class SimpleHandlerAnyMatcher extends GenericMatcher<HandleAny, HandleType> {

    public SimpleHandlerAnyMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleAny annotation, HandleType value) {
        return Arrays.asList(annotation.type()).contains(value);
    }

    @Override
    public Class<HandleAny> getType() {
        return HandleAny.class;
    }
}
