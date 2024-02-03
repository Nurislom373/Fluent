package org.khasanof.executors.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.constants.FluentConstants;
import org.khasanof.executors.determination.DeterminationFunction;
import org.khasanof.mediator.PerformMediator;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.service.interceptor.PreExecutionService;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.executors.processor
 * @since 2/1/2024 9:42 PM
 */
@Slf4j
public class DefaultChainProcessor extends AbstractUpdateChainProcessor {

    private final PerformMediator performMediator;
    private final ApplicationContext applicationContext;
    private final DeterminationFunction determinationFunction;

    public DefaultChainProcessor(PerformMediator performMediator,
                                 ApplicationContext applicationContext,
                                 PreExecutionService preExecutionService,
                                 DeterminationFunction determinationFunction) {

        super(preExecutionService);
        this.performMediator = performMediator;
        this.applicationContext = applicationContext;
        this.determinationFunction = determinationFunction;
    }

    @Override
    public void process(Update update) throws Exception {
        Set<SimpleInvoker> simpleInvokers = new LinkedHashSet<>();
        fillSimpleInvokers(update, simpleInvokers);
        internalProcess(simpleInvokers);
        callNextProcess(update);
    }

    private void fillSimpleInvokers(Update update, Set<SimpleInvoker> simpleInvokers) {
        determinationFunction.accept(applicationContext)
                .accept(update, simpleInvokers);
    }

    private void internalProcess(Set<SimpleInvoker> simpleInvokers) throws InvocationTargetException, IllegalAccessException {
        for (SimpleInvoker simpleInvoker : simpleInvokers) {
            callPreExecution(simpleInvoker);
            performMediator.execute(simpleInvoker);
        }
    }

    @Override
    public int getOrder() {
        return FluentConstants.LOW_ORDER;
    }
}
