package org.khasanof.event;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.khasanof.annotation.exception.HandleException;
import org.khasanof.collector.Collector;
import org.khasanof.event.exception.ThrowExceptionEvent;
import org.khasanof.executors.invoker.InvokerExecutor;
import org.khasanof.executors.invoker.InvokerFunctions;
import org.khasanof.executors.invoker.DefaultInvokerFunctions;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.event
 * @since 8/20/2023 5:55 PM
 */
@Slf4j
@Component
public class ThrowExceptionEventListener implements ApplicationListener<ThrowExceptionEvent> {

    private final InvokerExecutor invokerExecutor;
    private final InvokerFunctions invokerFunctions;
    private final Collector<Class<? extends Annotation>> collector;

    public ThrowExceptionEventListener(InvokerExecutor invoker, DefaultInvokerFunctions invokerFunctions, Collector<Class<? extends Annotation>> collector) {
        this.invokerExecutor = invoker;
        this.invokerFunctions = invokerFunctions;
        this.collector = collector;
    }

    @Override
    @SneakyThrows
    public void onApplicationEvent(@NotNull ThrowExceptionEvent event) {
        if (collector.hasHandle(HandleException.class)) {
            invokeExceptionHandler(event);
        } else {
            eventThrowException(event);
        }
    }

    private void invokeExceptionHandler(@NotNull ThrowExceptionEvent event) throws Throwable {
        Optional<SimpleInvoker> result = collector.getInvokerResult(event.getThrowable(), HandleException.class);
        if (result.isPresent()) {
            tryInvokeExceptionHandler(event, result.get());
        } else {
            eventThrowException(event);
        }
    }

    private void tryInvokeExceptionHandler(@NotNull ThrowExceptionEvent event, SimpleInvoker invoker) {
        invokerExecutor.invoke(invokerFunctions.adaptee(invoker, event.getUpdate(),
                event.getAbsSender(), event.getThrowable()));
    }

    private static void eventThrowException(ThrowExceptionEvent event) throws Throwable {
        throw event.getThrowable();
    }

}
