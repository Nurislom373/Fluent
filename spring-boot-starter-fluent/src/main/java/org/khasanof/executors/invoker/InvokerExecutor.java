package org.khasanof.executors.invoker;

import org.khasanof.annotation.exception.HandleException;
import org.khasanof.collector.Collector;
import org.khasanof.custom.FluentContext;
import org.khasanof.event.MethodV1Event;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.executors.execution.CommonExecutionAdapter;
import org.khasanof.model.Invoker;
import org.khasanof.model.invoker.SimpleInvoker;
import org.khasanof.utils.MethodUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 16.07.2023 15:01
 */
@Component
public class InvokerExecutor implements org.khasanof.executors.invoker.Invoker {

    private final Collector<Class<? extends Annotation>> collector;
    private final InvokerFunctions invokerFunctions;
    private final InvokerResultService resultService;
    private final CommonExecutionAdapter commonExecutionAdapter;
    private final ApplicationEventPublisher applicationEventPublisher;

    public InvokerExecutor(Collector<Class<? extends Annotation>> collector, InvokerFunctionsImpl invokerFunctions, InvokerResultService resultService, CommonExecutionAdapter commonExecutionAdapter, ApplicationEventPublisher applicationEventPublisher) {
        this.collector = collector;
        this.invokerFunctions = invokerFunctions;
        this.resultService = resultService;
        this.commonExecutionAdapter = commonExecutionAdapter;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void invoke(Invoker invokerModelV2) {
        try {
            absInvoker(invokerModelV2);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            try {
                exceptionDirector(e.getCause(), invokerModelV2);
                FluentContext.updateExecutorBoolean.set(true);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void absInvoker(Invoker invokerModel) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (Objects.isNull(invokerModel.getAdditionalParam())) {
            checkListParams(invokerModel.getMethodParams(), invokerModel.getArgs());
        }

        Map.Entry<Method, Object> classEntry = resultService.getResultEntry(invokerModel.getInvokerReference());
        Method method = classEntry.getKey();
        method.setAccessible(true);

        if (invokerModel.isCanBeNoParam() && method.getParameterCount() == 0) {
            method.invoke(classEntry.getValue());
        } else {
            commonExecutionAdapter.execution(new MethodV1Event(this, invokerModel, classEntry, method));
        }
    }

    private void checkListParams(List<Class<?>> params, Object[] args) {
        boolean allMatch = Arrays.stream(args).allMatch(arg -> params.contains(arg.getClass()) || params.stream()
                .anyMatch(any -> any.isAssignableFrom(arg.getClass())));
        if (!allMatch) {
            throw new InvalidParamsException("Param type doesn't match expected param!");
        }
    }

    private void exceptionDirector(Throwable throwable, Invoker prevInvoker) throws Throwable {
        SimpleInvoker exceptionHandleMethod = getExceptionHandleMethod(throwable);
        if (Objects.isNull(exceptionHandleMethod)) {
            throw throwable;
        }
        Invoker invokerModel = invokerFunctions.fillAndGet(exceptionHandleMethod, throwable,
                MethodUtils.getArg(prevInvoker.getArgs(), Update.class), MethodUtils.getArg(
                        prevInvoker.getArgs(), AbsSender.class));
        invoke(invokerModel);
    }

    private SimpleInvoker getExceptionHandleMethod(Throwable throwable) throws Throwable {
        if (collector.hasHandle(HandleException.class)) {
            return collector.getInvokerResult(throwable, HandleException.class);
        }
        throw throwable;
    }

}
