package org.khasanof.simulate;

import org.khasanof.FluentBot;
import org.khasanof.context.FluentThreadLocalContext;
import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.custom.BreakerForEach;
import org.khasanof.executors.UpdateExecutor;
import org.khasanof.executors.invoker.InvokerExecutor;
import org.khasanof.executors.invoker.InvokerFunctionsAdaptee;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.simulate
 * @since 1/9/2024 12:45 AM
 */
public class SimulateUpdateExecutor implements UpdateExecutor {

    private final InvokerExecutor invoker;
    private final GenericSingleton<FluentBot> fluentBot;
    private final InvokerFunctionsAdaptee invokerFunctionsAdaptee;
    private final SimulateDeterminationUpdate simulateDeterminationUpdate;

    public SimulateUpdateExecutor(InvokerExecutor invoker,
                                  GenericSingleton<FluentBot> fluentBot,
                                  InvokerFunctionsAdaptee invokerFunctionsAdaptee,
                                  SimulateDeterminationUpdate simulateDeterminationUpdate) {

        this.invoker = invoker;
        this.fluentBot = fluentBot;
        this.invokerFunctionsAdaptee = invokerFunctionsAdaptee;
        this.simulateDeterminationUpdate = simulateDeterminationUpdate;
    }

    @Override
    public void execute(Update update) {
        FluentBot instance = checkBotInstance();
        BreakerForEach.forEach(simulateDeterminationUpdate.determinationInvokers(update).stream(),
                ((entry, breaker) -> {
                    if (!FluentThreadLocalContext.updateExecutorBoolean.get()) {
                        invoker.invoke(invokerFunctionsAdaptee.adaptee(entry, update, instance));
                    } else {
                        breaker.stop();
                    }
                }), () -> FluentThreadLocalContext.updateExecutorBoolean.set(false));
    }

    private FluentBot checkBotInstance() {
        if (Objects.isNull(fluentBot.getInstance())) {
            throw new RuntimeException("Bot instance null!");
        }
        return fluentBot.getInstance();
    }
}
