package org.khasanof.executor.determination;

import org.khasanof.collector.Collector;
import org.khasanof.collector.StateCollector;
import org.khasanof.condition.Condition;
import org.khasanof.context.FluentThreadLocalContext;
import org.khasanof.enums.ProcessType;
import org.khasanof.executors.determination.DeterminationFunction;
import org.khasanof.models.invoker.SimpleInvoker;
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

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 16.07.2023 19:05
 */
@Component(HandleStateFunction.NAME)
@SuppressWarnings({"unchecked", "rawtypes"})
public class HandleStateFunction implements DeterminationFunction {

    public static final String NAME = "handleStateFunction";

    @Override
    public BiConsumer<Update, Set<SimpleInvoker>> accept(ApplicationContext applicationContext) {
        return ((update, invokerResults) -> {
            StateRepositoryStrategy repository = applicationContext.getBean(StateRepositoryStrategy.class);
            Long id = UpdateUtils.getUserId(update);

            Condition.isFalseThen(repository.existById(id))
                    .thenCall(() -> {
                        User from = UpdateUtils.getFrom(update);
                        if (Objects.nonNull(from)) {
                            repository.addState(from.getId());
                        }
                    });

            repository.findById(id).ifPresent(state -> {
                Collector<Enum> collector = applicationContext.getBean(StateCollector.NAME, Collector.class);
                collector.getInvokerResult(state.getState(), state.getState())
                        .ifPresent(simpleInvoker -> {
                            invokerResults.add(simpleInvoker);
                            Condition.isTrueThen(isNotProcessedUpdates(simpleInvoker))
                                    .thenCall(() -> FluentThreadLocalContext.determinationServiceBoolean.set(true));
                        });
            });
        });
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
