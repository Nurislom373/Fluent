package org.khasanof.collector.context.operation;

import org.khasanof.collector.context.ContextOperation;
import org.khasanof.models.collector.FindHandlerMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.service.SearchHandlerMethodService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.collector.context
 * @since 1/15/2024 10:32 PM
 */
@Component
public class FindHandlerMethodOperation implements ContextOperation<FindHandlerMethod, Optional<SimpleInvoker>> {

    private final SearchHandlerMethodService searchHandlerMethodService;

    public FindHandlerMethodOperation(SearchHandlerMethodService searchHandlerMethodService) {
        this.searchHandlerMethodService = searchHandlerMethodService;
    }

    @Override
    public Optional<SimpleInvoker> execute(FindHandlerMethod method) {
        return searchHandlerMethodService.find(method);
    }
}
