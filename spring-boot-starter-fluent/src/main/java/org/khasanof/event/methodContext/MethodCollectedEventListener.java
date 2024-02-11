package org.khasanof.event.methodContext;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.event.methodContext.MethodCollectedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/9/2023 10:21 AM
 */
@Slf4j
@Component
public class MethodCollectedEventListener implements ApplicationListener<MethodCollectedEvent> {

    @Override
    public void onApplicationEvent(MethodCollectedEvent event) {
        event.getLongMap().forEach((key, value) -> log.info("{} : {}", key, value));
    }

}
