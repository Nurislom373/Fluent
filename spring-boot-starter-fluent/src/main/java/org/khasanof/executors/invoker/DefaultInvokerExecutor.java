package org.khasanof.executors.invoker;

import org.khasanof.collector.context.ContextOperationExecutor;
import org.khasanof.collector.context.operation.ContainsHandlerMethodOperation;
import org.khasanof.collector.context.operation.FindHandlerMethodOperation;
import org.khasanof.context.FluentThreadLocalContext;
import org.khasanof.enums.HandleAnnotation;
import org.khasanof.event.ExecutionMethod;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.executors.execution.mediator.ExecutionMediator;
import org.khasanof.models.Invoker;
import org.khasanof.models.collector.FindHandlerMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.utils.MethodUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 16.07.2023 15:01
 */
@Component
public class DefaultInvokerExecutor implements InvokerExecutor {

    private final InvokerFunctions invokerFunctions;
    private final ExecutionMediator executionMediator;
    private final ContextOperationExecutor operationExecutor;

    public DefaultInvokerExecutor(InvokerFunctions invokerFunctions,
                                  ExecutionMediator executionMediator,
                                  ContextOperationExecutor operationExecutor) {

        this.invokerFunctions = invokerFunctions;
        this.executionMediator = executionMediator;
        this.operationExecutor = operationExecutor;
    }

    @Override
    public void invoke(Invoker invokerModelV2) {
        try {
            tryInvoker(invokerModelV2);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            resolveInvocationException(invokerModelV2, e);
        }
    }

    private void tryInvoker(Invoker invokerModel) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (Objects.isNull(invokerModel.getAdditionalParam())) {
            checkListParams(invokerModel.getMethodParams(), invokerModel.getArgs());
        }

        SimpleInvoker simpleInvoker = invokerModel.getInvokerReference();
        Method method = simpleInvoker.getMethod();
        tryAccessMethod(method);

        if (invokerModel.isCanBeNoParam() && method.getParameterCount() == 0) {
            method.invoke(simpleInvoker.getReference());
            return;
        }
        executionMediator.execution(new ExecutionMethod(this, invokerModel));
    }

    private void tryAccessMethod(Method method) throws IllegalAccessException {
        if (!method.trySetAccessible()) {
            throw new IllegalAccessException();
        }
    }

    private void checkListParams(List<Class<?>> params, Object[] args) {
        if (!isAllMatch(params, args)) {
            throw new InvalidParamsException("Param type doesn't match expected param!");
        }
    }

    private boolean isAllMatch(List<Class<?>> params, Object[] args) {
        return Arrays.stream(args)
                .allMatch(arg -> params.contains(arg.getClass()) || params.stream()
                        .anyMatch(any -> any.isAssignableFrom(arg.getClass())));
    }

    private void resolveInvocationException(Invoker invokerModelV2, InvocationTargetException e) {
        try {
            tryResolveInvocationException(e.getCause(), invokerModelV2);
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    private void tryResolveInvocationException(Throwable throwable, Invoker prevInvoker) throws Throwable {
        SimpleInvoker exceptionHandleMethod = getExceptionHandleMethod(throwable);
        Invoker invokerModel = getInvokerModel(throwable, prevInvoker, exceptionHandleMethod);

        invoke(invokerModel);
        FluentThreadLocalContext.updateExecutorBoolean.set(true);
    }

    private Invoker getInvokerModel(Throwable throwable, Invoker prevInvoker, SimpleInvoker exceptionHandleMethod) {
        return invokerFunctions.adaptee(exceptionHandleMethod, throwable, MethodUtils.getArg(prevInvoker.getArgs(), Update.class),
                MethodUtils.getArg(prevInvoker.getArgs(), AbsSender.class));
    }

    private SimpleInvoker getExceptionHandleMethod(Throwable throwable) throws Throwable {
        if (operationExecutor.execute(ContainsHandlerMethodOperation.class, HandleAnnotation.HANDLE_EXCEPTION)) {

            var handlerMethod = new FindHandlerMethod(throwable, HandleAnnotation.HANDLE_EXCEPTION);
            var invokerResult = operationExecutor.execute(FindHandlerMethodOperation.class, handlerMethod);

            if (invokerResult.isPresent()) {
                return invokerResult.get();
            }
        }
        throw throwable;
    }
}
