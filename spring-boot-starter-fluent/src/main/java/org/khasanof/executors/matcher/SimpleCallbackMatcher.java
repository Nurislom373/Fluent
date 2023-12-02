package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.config.ApplicationConstants;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

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

    public SimpleCallbackMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleCallback handleCallback, String value) {
        return Arrays.stream(handleCallback.values())
                .anyMatch(any -> matchFunctions.get(Map.entry(handleCallback.scope(),
                        getScopeType(value, handleCallback.scope()))).apply(any, value));
    }

    @Override
    public Class<HandleCallback> getType() {
        return HandleCallback.class;
    }

}
