package org.khasanof.event;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.exception.HandleException;
import org.khasanof.collector.Collector;
import org.khasanof.event.exceptionDirector.ExceptionDirectorEvent;
import org.khasanof.executors.invoker.InvokerFunctions;
import org.khasanof.executors.invoker.InvokerFunctionsImpl;
import org.khasanof.models.Invoker;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.event
 * @since 8/20/2023 5:55 PM
 */
@Slf4j
@Component
public class ExceptionDirectorEventListener implements ApplicationListener<ExceptionDirectorEvent> {

    private final org.khasanof.executors.invoker.Invoker invoker;
    private final InvokerFunctions invokerFunctions;
    private final Collector<Class<? extends Annotation>> collector;

    public ExceptionDirectorEventListener(org.khasanof.executors.invoker.Invoker invoker, InvokerFunctionsImpl invokerFunctions, Collector<Class<? extends Annotation>> collector) {
        this.invoker = invoker;
        this.invokerFunctions = invokerFunctions;
        this.collector = collector;
    }

    @Override
    @SneakyThrows
    public void onApplicationEvent(ExceptionDirectorEvent event) {
        if (collector.hasHandle(HandleException.class)) {
            SimpleInvoker result = collector.getInvokerResult(event.getThrowable(), HandleException.class);
            Invoker modelV2 = invokerFunctions.fillAndGet(result, event.getUpdate(), event.getAbsSender(), event.getThrowable());
            invoker.invoke(modelV2);
        } else {
            throw event.getThrowable();
        }
    }
}
