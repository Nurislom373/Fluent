package org.khasanof.executors.invoker;

import org.khasanof.annotation.exception.HandleException;
import org.khasanof.collector.Collector;
import org.khasanof.context.FluentThreadLocalContext;
import org.khasanof.event.ExecutionMethod;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.executors.execution.CommonExecutionAdapter;
import org.khasanof.models.Invoker;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.utils.MethodUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 16.07.2023 15:01
 */
@Component
public class DefaultInvokerExecutor implements InvokerExecutor {

    private final Collector<Class<? extends Annotation>> collector;
    private final InvokerFunctions invokerFunctions;
    private final CommonExecutionAdapter commonExecutionAdapter;

    public DefaultInvokerExecutor(Collector<Class<? extends Annotation>> collector, DefaultInvokerFunctions invokerFunctions,
                                  CommonExecutionAdapter commonExecutionAdapter) {
        this.collector = collector;
        this.invokerFunctions = invokerFunctions;
        this.commonExecutionAdapter = commonExecutionAdapter;
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
                FluentThreadLocalContext.updateExecutorBoolean.set(true);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void absInvoker(Invoker invokerModel) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (Objects.isNull(invokerModel.getAdditionalParam())) {
            checkListParams(invokerModel.getMethodParams(), invokerModel.getArgs());
        }

        SimpleInvoker simpleInvoker = invokerModel.getInvokerReference();
        Method method = simpleInvoker.getMethod();
        method.setAccessible(true);

        if (invokerModel.isCanBeNoParam() && method.getParameterCount() == 0) {
            method.invoke(simpleInvoker.getReference());
        } else {
            commonExecutionAdapter.execution(new ExecutionMethod(this, invokerModel));
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
        Invoker invokerModel = invokerFunctions.adaptee(exceptionHandleMethod, throwable,
                MethodUtils.getArg(prevInvoker.getArgs(), Update.class), MethodUtils.getArg(
                        prevInvoker.getArgs(), AbsSender.class));
        invoke(invokerModel);
    }

    private SimpleInvoker getExceptionHandleMethod(Throwable throwable) throws Throwable {
        if (collector.hasHandle(HandleException.class)) {
            Optional<SimpleInvoker> invokerResult = collector.getInvokerResult(throwable, HandleException.class);
            if (invokerResult.isPresent()) {
                return invokerResult.get();
            }
        }
        throw throwable;
    }

}
