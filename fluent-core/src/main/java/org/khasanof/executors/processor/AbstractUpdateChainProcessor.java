package org.khasanof.executors.processor;

import org.khasanof.SortOrder;
import org.khasanof.chain.AbstractNopChainProcessor;
import org.khasanof.executors.determination.DeterminationFunction;
import org.khasanof.mediator.PerformMediator;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.models.processor.SimpleInvokerFillerModel;
import org.khasanof.service.interceptor.PreExecutionService;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.executors.processor
 * @since 1/25/2024 10:28 PM
 */
public abstract class AbstractUpdateChainProcessor extends AbstractNopChainProcessor<Update> implements SortOrder {

    private PreExecutionService preExecutionService;

    public AbstractUpdateChainProcessor() {
    }

    public AbstractUpdateChainProcessor(PreExecutionService preExecutionService) {
        this.preExecutionService = preExecutionService;
    }

    protected void callNextProcess(Update update) throws Exception {
        if (Objects.nonNull(nextProcessor)) {
            nextProcessor.process(update);
        }
    }

    protected void callNextProcess(Update update, Boolean condition) throws Exception {
        if (Objects.nonNull(nextProcessor) && condition) {
            nextProcessor.process(update);
        }
    }

    protected void fillSimpleInvokers(Set<SimpleInvoker> simpleInvokers, SimpleInvokerFillerModel model) {
        model.accept(simpleInvokers);
    }

    protected void internalProcess(Set<SimpleInvoker> simpleInvokers, PerformMediator performMediator) throws InvocationTargetException, IllegalAccessException {
        for (SimpleInvoker simpleInvoker : simpleInvokers) {
            if (callPreExecution(simpleInvoker)) {
                performMediator.execute(simpleInvoker);
            }
        }
    }

    protected boolean callPreExecution(SimpleInvoker simpleInvoker) {
        if (Objects.nonNull(preExecutionService)) {
            return preExecutionService.preHandle(simpleInvoker);
        }
        return true;
    }
}
