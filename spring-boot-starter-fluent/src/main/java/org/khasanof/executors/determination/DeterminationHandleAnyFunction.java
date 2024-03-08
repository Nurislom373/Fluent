package org.khasanof.executors.determination;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.collector.context.ContextOperationExecutor;
import org.khasanof.collector.context.operation.ContainsHandlerMethodOperation;
import org.khasanof.collector.context.operation.FindMoreHandleAnyOperation;
import org.khasanof.context.FluentThreadLocalContext;
import org.khasanof.enums.HandleAnnotation;
import org.khasanof.enums.HandleType;
import org.khasanof.enums.Proceed;
import org.khasanof.enums.ProcessType;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.service.annotation.type.HandleTypeService;
import org.khasanof.service.interceptor.invoker.PostFindInvokerInterceptorService;
import org.khasanof.utils.InvokerUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

import static org.khasanof.utils.InvokerUtils.hasCondition;

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 16.07.2023 19:13
 */
@Slf4j
@Component(DeterminationHandleAnyFunction.NAME)
public class DeterminationHandleAnyFunction implements DeterminationFunction {

    public static final String NAME = "handleAnyFunction";
    private final HandleTypeService handleTypeService;
    private final ContextOperationExecutor operationExecutor;
    private final PostFindInvokerInterceptorService invokerInterceptorService;

    public DeterminationHandleAnyFunction(HandleTypeService handleTypeService,
                                          ContextOperationExecutor operationExecutor,
                                          PostFindInvokerInterceptorService invokerInterceptorService) {

        this.handleTypeService = handleTypeService;
        this.operationExecutor = operationExecutor;
        this.invokerInterceptorService = invokerInterceptorService;
    }

    @Override
    public BiConsumer<Update, Set<SimpleInvoker>> getConsumer(ApplicationContext applicationContext) {
        return ((update, invokerResults) -> {
            if (operationExecutor.execute(ContainsHandlerMethodOperation.class, HandleAnnotation.HANDLE_ANY)) {
                internalAccept(update, invokerResults);
            }
        });
    }

    private void internalAccept(Update update, Set<SimpleInvoker> invokerResults) {
        addAllTypeHandleAnyInvokers(invokerResults);
        handleTypeService.findAllByUpdate(update)
                .forEach(handleType -> foundMethodsAddInvokers(invokerResults, handleType));
    }

    private void addAllTypeHandleAnyInvokers(Set<SimpleInvoker> invokerResults) {
        var invokers = operationExecutor.execute(FindMoreHandleAnyOperation.class, HandleType.ALL);

        if (Objects.isNull(invokers) || invokers.isEmpty()) {
            return;
        }
        checkInvokerThenAdd(invokerResults, invokers);
    }

    private void foundMethodsAddInvokers(Set<SimpleInvoker> invokerResults, HandleType handleType) {
        var allHandleAnyMethods = operationExecutor.execute(FindMoreHandleAnyOperation.class, handleType);

        if (Objects.isNull(allHandleAnyMethods) || allHandleAnyMethods.isEmpty()) {
            return;
        }
        checkInvokerThenAdd(invokerResults, allHandleAnyMethods);
    }

    private void checkInvokerThenAdd(Set<SimpleInvoker> invokerResults, Set<SimpleInvoker> invokers) {
        invokers.forEach(simpleInvoker -> {
            if (hasCondition(simpleInvoker) && !invokerInterceptorService.intercept(simpleInvoker)) {
                return;
            }
            invokerResults.add(simpleInvoker);
        });
        isCanProcess(invokers);
    }

    private void isCanProcess(Set<SimpleInvoker> allHandleAnyMethods) {
        if (hasValueNotProceedInMethods(allHandleAnyMethods)) {
            FluentThreadLocalContext.determinationServiceBoolean.set(true);
        }
    }

    private boolean hasValueNotProceedInMethods(Set<SimpleInvoker> methods) {
        return methods.stream()
                .map(SimpleInvoker::getMethod)
                .anyMatch(method -> Objects.equals(getProceed(method), Proceed.NOT_PROCEED));
    }

    private Proceed getProceed(Method method) {
        return method.getAnnotation(HandleAny.class).proceed();
    }

    @Override
    public Integer getOrder() {
        return 1;
    }

    @Override
    public ProcessType processType() {
        return ProcessType.BOTH;
    }
}
