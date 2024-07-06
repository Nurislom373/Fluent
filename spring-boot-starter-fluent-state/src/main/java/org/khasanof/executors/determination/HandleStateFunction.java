package org.khasanof.executors.determination;

import org.khasanof.collector.context.ContextOperationExecutor;
import org.khasanof.collector.context.operation.FindHandlerObjectOperation;
import org.khasanof.condition.Condition;
import org.khasanof.enums.ProcessType;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.registry.state.UserProceedStateRegistryContainer;
import org.khasanof.service.interceptor.invoker.PostFindInvokerInterceptorService;
import org.khasanof.state.StateAction;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.khasanof.utils.UpdateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

import static org.khasanof.utils.InvokerUtils.hasCondition;

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 16.07.2023 19:05
 */
@SuppressWarnings({"rawtypes"})
@Component(HandleStateFunction.NAME)
public class HandleStateFunction implements DeterminationFunction {

    public static final String NAME = "handleStateFunction";
    private final ContextOperationExecutor operationExecutor;
    private final PostFindInvokerInterceptorService invokerInterceptorService;
    private final UserProceedStateRegistryContainer proceedStateRegistryContainer;

    public HandleStateFunction(ContextOperationExecutor operationExecutor,
                               PostFindInvokerInterceptorService invokerInterceptorService,
                               UserProceedStateRegistryContainer proceedStateRegistryContainer) {

        this.operationExecutor = operationExecutor;
        this.invokerInterceptorService = invokerInterceptorService;
        this.proceedStateRegistryContainer = proceedStateRegistryContainer;
    }

    @Override
    public BiConsumer<Update, Set<SimpleInvoker>> getConsumer(ApplicationContext applicationContext) {
        return ((update, invokerResults) -> {
            StateRepositoryStrategy repository = applicationContext.getBean(StateRepositoryStrategy.class);
            Long id = UpdateUtils.getUserId(update);

            checkUserState(update, repository, id);
            findHandlerObject(invokerResults, repository, id);
        });
    }

    private void checkUserState(Update update, StateRepositoryStrategy repository, Long id) {
        Condition.isFalseThen(repository.existById(id))
                .thenCall(() -> {
                    User from = UpdateUtils.getFrom(update);
                    if (Objects.nonNull(from)) {
                        repository.addState(from.getId());
                    }
                });
    }

    private void findHandlerObject(Set<SimpleInvoker> invokerResults, StateRepositoryStrategy repository, Long id) {
        repository.findById(id)
                .flatMap(state -> operationExecutor.execute(FindHandlerObjectOperation.class, state.getState()))
                .ifPresentOrElse(simpleInvoker -> checkConditionThenAddResult(id, invokerResults, simpleInvoker), () -> {
                    replaceUserProceedState(id, false);
                });
    }

    private void checkConditionThenAddResult(Long userId, Set<SimpleInvoker> invokerResults, SimpleInvoker simpleInvoker) {
        if (hasCondition(simpleInvoker) && !invokerInterceptorService.intercept(simpleInvoker)) {
            return;
        }
        invokerResults.add(simpleInvoker);
        replaceUserProceedState(userId, isNotProcessedUpdates(simpleInvoker));
    }

    private void replaceUserProceedState(Long userId, Boolean notProcessedUpdates) {
        proceedStateRegistryContainer.add(userId.toString(), notProcessedUpdates);
    }

    private boolean isNotProcessedUpdates(SimpleInvoker result) {
        StateAction stateActions = (StateAction) result.getReference();
        return !stateActions.updateHandlersProceed();
    }

    @Override
    public Integer getOrder() {
        return 5;
    }

    @Override
    public ProcessType processType() {
        return ProcessType.STATE;
    }
}
