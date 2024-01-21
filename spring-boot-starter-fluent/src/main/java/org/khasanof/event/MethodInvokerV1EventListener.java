package org.khasanof.event;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.executors.execution.mediator.ExecutionMediator;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.event
 * @since 8/13/2023 8:56 PM
 */
@Slf4j
@Component
public class MethodInvokerV1EventListener {

    private final ExecutionMediator executionMediator;

    public MethodInvokerV1EventListener(ExecutionMediator executionMediator) {
        this.executionMediator = executionMediator;
    }

    @Async
    @EventListener(value = ExecutionMethod.class)
    public void onApplicationEvent(ExecutionMethod methodV1Event) {
        log.debug("event listen invoker name : {}", methodV1Event.getInvokerModel().getName());
        executionMediator.execution(methodV1Event);
    }
}
