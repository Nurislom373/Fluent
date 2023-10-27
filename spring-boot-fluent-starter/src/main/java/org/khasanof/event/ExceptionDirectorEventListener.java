package org.khasanof.event;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.exception.HandleException;
import org.khasanof.collector.Collector;
import org.khasanof.event.exceptionDirector.ExceptionDirectorEvent;
import org.khasanof.executors.invoker.Invoker;
import org.khasanof.executors.invoker.InvokerFunctions;
import org.khasanof.executors.invoker.InvokerFunctionsImpl;
import org.khasanof.model.InvokerModel;
import org.khasanof.model.InvokerResult;
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

    private final Invoker invoker;
    private final InvokerFunctions invokerFunctions;
    private final Collector<Class<? extends Annotation>> collector;

    public ExceptionDirectorEventListener(Invoker invoker, InvokerFunctionsImpl invokerFunctions, Collector<Class<? extends Annotation>> collector) {
        this.invoker = invoker;
        this.invokerFunctions = invokerFunctions;
        this.collector = collector;
    }

    @Override
    @SneakyThrows
    public void onApplicationEvent(ExceptionDirectorEvent event) {
        if (collector.hasHandle(HandleException.class)) {
            InvokerResult result = collector.getInvokerResult(event.getThrowable(), HandleException.class);
            InvokerModel modelV2 = invokerFunctions.fillAndGet(result, event.getUpdate(), event.getAbsSender(), event.getThrowable());
            invoker.invoke(modelV2);
        } else {
            throw event.getThrowable();
        }
    }
}
