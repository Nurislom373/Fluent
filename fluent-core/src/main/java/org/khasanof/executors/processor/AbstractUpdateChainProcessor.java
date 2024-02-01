package org.khasanof.executors.processor;

import org.khasanof.SortOrder;
import org.khasanof.chain.AbstractNopChainProcessor;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.service.interceptor.PreExecutionService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

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

    protected void callPreExecution(SimpleInvoker simpleInvoker) {
        if (Objects.nonNull(preExecutionService)) {
            preExecutionService.preHandle(simpleInvoker);
        }
    }
}
