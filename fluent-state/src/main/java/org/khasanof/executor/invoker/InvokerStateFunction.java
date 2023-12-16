package org.khasanof.executor.invoker;

import org.khasanof.enums.InvokerType;
import org.khasanof.executors.invoker.InvokerFunctions;
import org.khasanof.models.Invoker;
import org.khasanof.models.additional.param.APUpdateState;
import org.khasanof.models.condition.ClassCondition;
import org.khasanof.state.State;
import org.khasanof.state.StateAction;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.khasanof.utils.UpdateUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.executor.invoker
 * @since 10/13/2023 11:36 PM
 */
@Component
public class InvokerStateFunction implements InitializingBean {

    private final InvokerFunctions functions;
    private final StateRepositoryStrategy stateRepository;
    public static final String HANDLE_STATE = "handleState";

    public InvokerStateFunction(InvokerFunctions functions, StateRepositoryStrategy stateRepository) {
        this.functions = functions;
        this.stateRepository = stateRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Invoker stateInvokerModel = Invoker.builder()
                .name(HANDLE_STATE)
                .type(InvokerType.CLASS)
                .condition((ClassCondition) (invokerClass -> StateAction.class.isAssignableFrom(
                        invokerClass.getReference().getClass())))
                .additionalParam((APUpdateState)
                        (update -> stateRepository.findById(UpdateUtils.getUserId(update))
                                .orElseThrow(() -> new RuntimeException("State not found by userId!"))))
                .methodParams(List.of(Update.class, AbsSender.class, State.class))
                .canBeNoParam(false)
                .isInputSystem(false)
                .build();
        functions.add(stateInvokerModel);
    }

}
