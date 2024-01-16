package org.khasanof.collector.context.operation;

import org.khasanof.collector.StateMethodContext;
import org.khasanof.collector.context.ContextOperation;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
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
    private final InvokerMethodFactory invokerMethodFactory;

    public FindHandlerObjectOperation(StateMethodContext methodContext,
                                      InvokerMethodFactory invokerMethodFactory) {

        this.methodContext = methodContext;
        this.invokerMethodFactory = invokerMethodFactory;
    }

    @Override
    public Optional<SimpleInvoker> execute(Enum state) {
        return methodContext.find(state)
                .map(invokerMethodFactory::create);
    }

}
