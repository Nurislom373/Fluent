package org.khasanof.service.exception;

import org.khasanof.collector.context.ContextOperationExecutor;
import org.khasanof.collector.context.operation.ContainsHandlerMethodOperation;
import org.khasanof.collector.context.operation.FindHandlerMethodOperation;
import org.khasanof.enums.HandleAnnotation;
import org.khasanof.mediator.PerformMediator;
import org.khasanof.models.collector.FindHandlerMethod;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Nurislom
 * @see org.khasanof.service.exception
 * @since 2/1/2024 11:39 PM
 */
@Component
public class DefaultExceptionResolverService implements ExceptionResolverService {

    private final PerformMediator performMediator;
    private final ContextOperationExecutor operationExecutor;

    public DefaultExceptionResolverService(PerformMediator performMediator,
                                           ContextOperationExecutor operationExecutor) {

        this.performMediator = performMediator;
        this.operationExecutor = operationExecutor;
    }

    @Override
    public void resolve(ExceptionResolver resolver) throws Exception {
        if (operationExecutor.execute(ContainsHandlerMethodOperation.class, HandleAnnotation.HANDLE_EXCEPTION)) {

            var handlerMethod = new FindHandlerMethod(resolver.getThrowable().getCause(), HandleAnnotation.HANDLE_EXCEPTION);
            var invokerResult = operationExecutor.execute(FindHandlerMethodOperation.class, handlerMethod);

            if (invokerResult.isPresent()) {
                perform(resolver, invokerResult.get());
                return;
            }
        }
        throw new RuntimeException(resolver.getThrowable());
    }

    private void perform(ExceptionResolver resolver, SimpleInvoker simpleInvoker) throws InvocationTargetException, IllegalAccessException {
        fillParam(simpleInvoker, resolver);
        performMediator.execute(simpleInvoker);
    }

    private void fillParam(SimpleInvoker simpleInvoker, ExceptionResolver resolver) {
        simpleInvoker.getParams().put(InvokerParam.ADDITIONAL_PARAM, resolver.getThrowable().getCause());
    }
}
