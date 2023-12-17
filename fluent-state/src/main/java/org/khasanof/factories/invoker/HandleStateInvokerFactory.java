package org.khasanof.factories.invoker;

import org.jetbrains.annotations.NotNull;
import org.khasanof.enums.InvokerType;
import org.khasanof.models.Invoker;
import org.khasanof.models.additional.param.APUpdateState;
import org.khasanof.models.condition.ClassCondition;
import org.khasanof.state.State;
import org.khasanof.state.StateAction;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.khasanof.utils.UpdateUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.factories.invoker
 * @since 12/17/2023 3:58 PM
 */
@Component(HandleStateInvokerFactory.NAME)
public class HandleStateInvokerFactory implements InvokerFactory {

    public static final String NAME = "handleStateInvokerFactory";
    public static final String HANDLE_STATE = "handleState";
    private final StateRepositoryStrategy stateRepository;

    public HandleStateInvokerFactory(StateRepositoryStrategy stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public Invoker create() {
        return Invoker.builder()
                .name(HANDLE_STATE)
                .type(InvokerType.CLASS)
                .condition(getClassCondition())
                .additionalParam(getAdditionalParam())
                .methodParams(List.of(Update.class, AbsSender.class, State.class))
                .canBeNoParam(false)
                .isInputSystem(false)
                .build();
    }

    @NotNull
    private APUpdateState getAdditionalParam() {
        return update -> stateRepository.findById(UpdateUtils.getUserId(update))
                .orElseThrow(() -> new RuntimeException("State not found by userId!"));
    }

    @NotNull
    private static ClassCondition getClassCondition() {
        return invokerClass -> StateAction.class.isAssignableFrom(invokerClass.getReference().getClass());
    }

}
