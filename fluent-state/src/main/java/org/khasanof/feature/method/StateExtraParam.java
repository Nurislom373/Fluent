package org.khasanof.feature.method;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.context.FluentUpdate;
import org.khasanof.enums.MethodType;
import org.khasanof.feature.HandleMethodExtraParam;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.state.State;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.khasanof.utils.UpdateUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.feature.method
 * @since 2/8/2024 10:18 PM
 */
@Component
public class StateExtraParam implements HandleMethodExtraParam {

    private final StateRepositoryStrategy stateRepositoryStrategy;

    public StateExtraParam(StateRepositoryStrategy stateRepositoryStrategy) {
        this.stateRepositoryStrategy = stateRepositoryStrategy;
    }

    @Override
    public void execute(SimpleInvoker simpleInvoker) {
        FluentUpdate currentUpdate = FluentContextHolder.getCurrentUpdate();
        simpleInvoker.getParams().put(InvokerParam.ADDITIONAL_PARAM, getCurrentUserState(currentUpdate.getUpdate()));
    }

    private State getCurrentUserState(Update update) {
        return stateRepositoryStrategy.findById(UpdateUtils.getUserId(update))
                .orElseThrow(() -> new RuntimeException("State not found by userId!"));
    }

    @Override
    public MethodType methodType() {
        return MethodType.STATE;
    }
}
