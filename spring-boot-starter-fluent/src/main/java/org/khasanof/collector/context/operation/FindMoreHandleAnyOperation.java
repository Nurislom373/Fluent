package org.khasanof.collector.context.operation;

import org.khasanof.collector.context.ContextOperation;
import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.enums.HandleAnnotation;
import org.khasanof.enums.HandleType;
import org.khasanof.executors.matcher.CommonMatcherMediator;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.collector.context.operation
 * @since 1/15/2024 11:37 PM
 */
@Component
public class FindMoreHandleAnyOperation implements ContextOperation<HandleType, Set<SimpleInvoker>> {

    private final CommonMatcherMediator matcher;
    private final SimpleMethodContext methodContext;

    public FindMoreHandleAnyOperation(CommonMatcherMediator matcher,
                                      SimpleMethodContext methodContext) {

        this.matcher = matcher;
        this.methodContext = methodContext;
    }

    @Override
    public Set<SimpleInvoker> execute(HandleType handleType) {
        return methodContext.find(HandleAnnotation.HANDLE_ANY)
                .map(functionRefMap -> functionRefMap.stream()
                        .filter(simpleInvoker -> matcher.match(simpleInvoker.getMethod(), handleType))
                        .collect(Collectors.toSet())
                ).orElse(Collections.emptySet());
    }
}
