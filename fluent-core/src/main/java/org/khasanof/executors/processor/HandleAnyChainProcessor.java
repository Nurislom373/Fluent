package org.khasanof.executors.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.constants.FluentConstants;
import org.khasanof.executors.determination.DeterminationFunction;
import org.khasanof.executors.execution.Perform;
import org.khasanof.mediator.PerformMediator;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.executors.processor
 * @since 1/27/2024 6:39 PM
 */
@Slf4j
public class HandleAnyChainProcessor extends AbstractUpdateChainProcessor {

    private final PerformMediator performMediator;
    private final ApplicationContext applicationContext;
    private final DeterminationFunction determinationFunction;

    public HandleAnyChainProcessor(PerformMediator performMediator,
                                   ApplicationContext applicationContext,
                                   DeterminationFunction determinationFunction) {

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
            performMediator.execute(simpleInvoker);
        }
    }

    @Override
    public int getOrder() {
        return FluentConstants.DEFAULT_ORDER;
    }
}
