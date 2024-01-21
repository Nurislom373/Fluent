package org.khasanof.event;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.khasanof.collector.context.ContextOperationExecutor;
import org.khasanof.collector.context.operation.ContainsHandlerMethodOperation;
import org.khasanof.collector.context.operation.FindHandlerMethodOperation;
import org.khasanof.enums.HandleAnnotation;
import org.khasanof.event.exception.ResolveExceptionEvent;
import org.khasanof.executors.invoker.InvokerExecutor;
import org.khasanof.executors.invoker.InvokerFunctions;
import org.khasanof.executors.invoker.DefaultInvokerFunctions;
import org.khasanof.models.collector.FindHandlerMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.event
 * @since 8/20/2023 5:55 PM
 */
@Slf4j
@Component
public class ResolveExceptionEventListener implements ApplicationListener<ResolveExceptionEvent> {

    private final InvokerExecutor invokerExecutor;
    private final InvokerFunctions invokerFunctions;
    private final ContextOperationExecutor operationExecutor;

    public ResolveExceptionEventListener(InvokerExecutor invoker,
                                         DefaultInvokerFunctions invokerFunctions,
                                         ContextOperationExecutor operationExecutor) {

        this.invokerExecutor = invoker;
        this.invokerFunctions = invokerFunctions;
        this.operationExecutor = operationExecutor;
    }

    @Override
    public void onApplicationEvent(@NotNull ResolveExceptionEvent event) {
        try {
            tryResolveException(event);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void tryResolveException(ResolveExceptionEvent event) throws Throwable {
        if (!operationExecutor.execute(ContainsHandlerMethodOperation.class, HandleAnnotation.HANDLE_EXCEPTION)) {
            eventThrowException(event);
        }
        invokeExceptionHandler(event);
    }

    private void invokeExceptionHandler(@NotNull ResolveExceptionEvent event) throws Throwable {
        FindHandlerMethod handlerMethod = new FindHandlerMethod(event.getThrowable(), HandleAnnotation.HANDLE_EXCEPTION);
        Optional<SimpleInvoker> optional = operationExecutor.execute(FindHandlerMethodOperation.class, handlerMethod);

        if (optional.isEmpty()) {
            eventThrowException(event);
        }
        invokeExceptionHandlerMethod(event, optional.get());
    }

    private void invokeExceptionHandlerMethod(@NotNull ResolveExceptionEvent event, SimpleInvoker invoker) {
        invokerExecutor.invoke(invokerFunctions.adaptee(invoker, event.getUpdate(),
                event.getAbsSender(), event.getThrowable()));
    }

    private void eventThrowException(ResolveExceptionEvent event) throws Throwable {
        throw event.getThrowable();
    }

}
