package org.khasanof.event;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.executors.execution.CommonExecutionAdapter;
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

    private final CommonExecutionAdapter commonExecutionAdapter;

    public MethodInvokerV1EventListener(CommonExecutionAdapter commonExecutionAdapter) {
        this.commonExecutionAdapter = commonExecutionAdapter;
    }

    @Async
    @EventListener(value = ExecutionMethod.class)
    public void onApplicationEvent(ExecutionMethod methodV1Event) {
        log.debug("event listen invoker name : {}", methodV1Event.getInvokerModel().getName());
        commonExecutionAdapter.execution(methodV1Event);
    }

}
