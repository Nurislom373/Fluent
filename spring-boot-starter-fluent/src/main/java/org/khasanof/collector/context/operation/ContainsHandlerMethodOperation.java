package org.khasanof.collector.context.operation;

import org.khasanof.collector.context.ContextOperation;
import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.enums.HandleClasses;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.collector.context.operation
 * @since 1/15/2024 10:51 PM
 */
@Component
public class ContainsHandlerMethodOperation implements ContextOperation<HandleClasses, Boolean> {

    private final SimpleMethodContext methodContext;

    public ContainsHandlerMethodOperation(SimpleMethodContext methodContext) {
        this.methodContext = methodContext;
    }

    @Override
    public Boolean execute(HandleClasses handleClasses) {
        return methodContext.contains(handleClasses);
    }
}
