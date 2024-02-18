package org.khasanof.executors.determination;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.context.ContextOperationExecutor;
import org.khasanof.collector.context.operation.FindHandlerMethodOperation;
import org.khasanof.enums.ProcessType;
import org.khasanof.executors.appropriate.determining.AppropriateDetermining;
import org.khasanof.models.collector.FindHandlerMethod;
import org.khasanof.models.executors.AppropriateMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.service.annotation.handler.AnnotationHandlerService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 16.07.2023 18:58
 */
@Slf4j
@Component(DeterminationHandleUpdateFunction.NAME)
public class DeterminationHandleUpdateFunction implements DeterminationFunction {

    public static final String NAME = "handleUpdateFunction";
    private final ContextOperationExecutor operationExecutor;
    private final AnnotationHandlerService annotationHandlerService;

    public DeterminationHandleUpdateFunction(ContextOperationExecutor contextOperationExecutor,
                                             AnnotationHandlerService annotationHandlerService) {

        this.operationExecutor = contextOperationExecutor;
        this.annotationHandlerService = annotationHandlerService;
    }

    @Override
    public BiConsumer<Update, Set<SimpleInvoker>> accept(ApplicationContext applicationContext) {
        return ((update, invokerResults) -> {
            var appropriateDetermining = applicationContext.getBean(AppropriateDetermining.class);

            appropriateDetermining.determining(update)
                    .ifPresentOrElse(appropriateMethod -> internalAccept(invokerResults, appropriateMethod),
                            () -> log.warn("Method not found!"));
        });
    }

    private void internalAccept(Set<SimpleInvoker> invokerResults, AppropriateMethod appropriateMethod) {
        addInvokersToResultSet(invokerResults, appropriateMethod);
    }

    private void addInvokersToResultSet(Set<SimpleInvoker> invokerResults, AppropriateMethod appropriateMethod) {
        annotationHandlerService.findByAnnotationClass(appropriateMethod.getAnnotation())
                .ifPresent(handler -> {
                    var handlerMethod = new FindHandlerMethod(appropriateMethod.getValue(), handler);
                    operationExecutor.execute(FindHandlerMethodOperation.class, handlerMethod)
                            .ifPresentOrElse(invokerResults::add, () -> log.warn("Method not found!"));
                });
    }

    @Override
    public Integer getOrder() {
        return 10;
    }

    @Override
    public ProcessType processType() {
        return ProcessType.UPDATE;
    }
}
