package org.khasanof.event.assembleMethods;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.AssembleMethods;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author Nurislom
 * @see org.khasanof.event
 * @since 8/19/2023 4:55 PM
 */
@Slf4j
@Component
public class AssembleMethodsListener implements ApplicationListener<AssembleMethodsEvent> {

    private final ApplicationContext applicationContext;

    public AssembleMethodsListener(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(AssembleMethodsEvent event) {
        log.info("assemble methods start!");
        applicationContext.getBeansOfType(AssembleMethods.class)
                .forEach(((s, assembleMethods) -> CompletableFuture.runAsync(assembleMethods::assembleMethods)));
    }

}
