package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.config.ApplicationConstants;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 25.06.2023 22:50
 */
@Component
public class SimpleMessageMatcher extends GenericMatcher<HandleMessage, String> {

    public SimpleMessageMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleMessage handleMessage, String value) {
        return matchFunctions.get(Map.entry(handleMessage.scope(), getScopeType(value, handleMessage.scope())))
                .apply(handleMessage.value(), value);
    }

    @Override
    public Class<HandleMessage> getType() {
        return HandleMessage.class;
    }

}
