package org.khasanof.executor.determination;

import org.khasanof.collector.Collector;
import org.khasanof.collector.StateCollector;
import org.khasanof.condition.Condition;
import org.khasanof.custom.FluentContext;
import org.khasanof.enums.ProcessType;
import org.khasanof.executors.determination.DeterminationFunction;
import org.khasanof.models.invoker.SimpleInvokerObject;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.state.StateAction;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.khasanof.utils.UpdateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 16.07.2023 19:05
 */
@Component(HandleStateFunction.NAME)
public class HandleStateFunction implements DeterminationFunction {

    public static final String NAME = "handleStateFunction";

    @Override
    @SuppressWarnings("unchecked")
    public BiConsumer<Update, Set<SimpleInvoker>> accept(ApplicationContext applicationContext) {
        return ((update, invokerResults) -> {
            StateRepositoryStrategy repository = applicationContext.getBean(StateRepositoryStrategy.class);
            Long id = UpdateUtils.getUserId(update);

            Condition.isFalseThen(repository.existById(id))
                    .thenCall(() -> repository.addState(UpdateUtils.getFrom(update).getId()));

            repository.findById(id).ifPresent(state -> {
                Collector<Enum> collector = applicationContext.getBean(StateCollector.NAME, Collector.class);
                SimpleInvoker classEntry = collector.getInvokerResult(state.getState(), state.getState());

                Condition.isTrue(Objects.nonNull(classEntry))
                        .thenCall(() -> {
                            invokerResults.add(classEntry);
                            Condition.isTrueThen(isNotProcessedUpdates(classEntry))
                                    .thenCall(() -> {
                                        FluentContext.determinationServiceBoolean.set(true);
                                    });
                        })
                        .elseDoNothing();
            });
        });
    }

    private boolean isNotProcessedUpdates(SimpleInvoker result) {
        SimpleInvokerObject invokerObject = (SimpleInvokerObject) result;
        StateAction stateActions = (StateAction) invokerObject.getReference();
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
