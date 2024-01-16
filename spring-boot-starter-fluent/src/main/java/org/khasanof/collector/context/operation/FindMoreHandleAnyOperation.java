package org.khasanof.collector.context.operation;

import org.khasanof.collector.context.ContextOperation;
import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.enums.HandleClasses;
import org.khasanof.enums.HandleType;
import org.khasanof.executors.matcher.CommonMatcher;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
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

    private final CommonMatcher matcher;
    private final SimpleMethodContext methodContext;
    private final InvokerMethodFactory invokerMethodFactory;

    public FindMoreHandleAnyOperation(CommonMatcher matcher,
                                      SimpleMethodContext methodContext,
                                      InvokerMethodFactory invokerMethodFactory) {

        this.matcher = matcher;
        this.methodContext = methodContext;
        this.invokerMethodFactory = invokerMethodFactory;
    }

    @Override
    public Set<SimpleInvoker> execute(HandleType handleType) {
        return methodContext.find(HandleClasses.HANDLE_ANY)
                .map(functionRefMap -> functionRefMap.entrySet().stream().filter(
                                clazz -> matcher.chooseMatcher(clazz.getKey(), handleType))
                        .map(invokerMethodFactory::create)
                        .collect(Collectors.toSet())
                ).orElse(Collections.emptySet());
    }
}
