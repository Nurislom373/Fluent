package org.khasanof.executors.processor;

import org.jetbrains.annotations.NotNull;
import org.khasanof.constants.StateConstants;
import org.khasanof.executors.determination.DeterminationFunction;
import org.khasanof.mediator.PerformMediator;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.registry.state.UserProceedStateRegistryContainer;
import org.khasanof.service.interceptor.PreExecutionService;
import org.khasanof.utils.UpdateUtils;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.executors.processor
 * @since 2/8/2024 10:09 PM
 */
public class StateChainProcessor extends AbstractUpdateChainProcessor {

    private final PerformMediator performMediator;
    private final ApplicationContext applicationContext;
    private final DeterminationFunction determinationFunction;
    private final UserProceedStateRegistryContainer proceedStateRegistryContainer;

    public StateChainProcessor(PerformMediator performMediator,
                               ApplicationContext applicationContext,
                               PreExecutionService preExecutionService,
                               DeterminationFunction determinationFunction,
                               UserProceedStateRegistryContainer proceedStateRegistryContainer) {

        super(preExecutionService);
        this.performMediator = performMediator;
        this.applicationContext = applicationContext;
        this.determinationFunction = determinationFunction;
        this.proceedStateRegistryContainer = proceedStateRegistryContainer;
    }

    @Override
    public void process(Update update) throws Exception {
        Set<SimpleInvoker> simpleInvokers = new LinkedHashSet<>();
        fillSimpleInvokers(update, simpleInvokers);
        internalProcess(simpleInvokers, performMediator);
        callNextProcess(update, !getHasNextProceed(update));
    }

    private void fillSimpleInvokers(Update update, Set<SimpleInvoker> simpleInvokers) {
        determinationFunction.getConsumer(applicationContext)
                .accept(update, simpleInvokers);
    }

    private boolean getHasNextProceed(Update update) {
        Long userId = UpdateUtils.getUserId(update);
        if (userId == null) {
            return false;
        }
        return internalGetHasNextProceed(userId);
    }

    private Boolean internalGetHasNextProceed(Long userId) {
        return this.proceedStateRegistryContainer.getProceedState(userId.toString())
                .orElse(false);
    }

    @Override
    public int getOrder() {
        return StateConstants.STATE_ORDER;
    }
}
