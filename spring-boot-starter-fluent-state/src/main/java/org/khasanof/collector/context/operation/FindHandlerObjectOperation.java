package org.khasanof.collector.context.operation;

import org.khasanof.collector.StateMethodContext;
import org.khasanof.collector.context.ContextOperation;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.collector.context.operation
 * @since 1/16/2024 9:41 PM
 */
@Component
@SuppressWarnings({"rawtypes"})
public class FindHandlerObjectOperation implements ContextOperation<Enum, Optional<SimpleInvoker>> {

    private final StateMethodContext methodContext;

    public FindHandlerObjectOperation(StateMethodContext methodContext) {
        this.methodContext = methodContext;
    }

    @Override
    public Optional<SimpleInvoker> execute(Enum state) {
        return methodContext.find(state);
    }
}
