package org.khasanof.executors;

import org.khasanof.FluentBot;
import org.khasanof.custom.BreakerForEach;
import org.khasanof.custom.FluentContext;
import org.khasanof.executors.invoker.Invoker;
import org.khasanof.executors.invoker.InvokerFunctions;
import org.khasanof.executors.invoker.InvokerFunctionsImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author <a href="https://github.com/Nurislom373">Nurislom</a>
 * @version 1.0.5
 * @see org.khasanof.executors
 * @since 24.06.2023 0:46
 */
@Component
public class CommonUpdateExecutor extends AbstractUpdateExecutor {

    private final InvokerFunctions invokerFunctions; // specifies the methods that can be invoked.
    private final DeterminationUpdate determination; // gathers the methods corresponding to the incoming update.
    private final Invoker invoker; // method invoker
    private final FluentBot fluentBot; // bot instance

    public CommonUpdateExecutor(InvokerFunctionsImpl invokerFunctions, DeterminationUpdate determinationUpdateType, Invoker invoker, @Lazy FluentBot fluentBot) {
        this.invokerFunctions = invokerFunctions;
        this.determination = determinationUpdateType;
        this.invoker = invoker;
        this.fluentBot = fluentBot;
    }

    /**
     * Using the {@link DeterminationUpdate#determinationV2(Update)} method, update collects the matching methods.
     * iterates the collected methods one by one. If an error or something happens, the execution of methods is stopped.
     *
     * @param update from telegram is coming.
     */
    @Override
    public void execute(Update update) {
        BreakerForEach.forEach(determination.determinationV2(update).stream(),
                ((entry, breaker) -> {
                    if (!FluentContext.updateExecutorBoolean.get()) {
                        invoker.invoke(invokerFunctions.fillAndGet(entry, update, fluentBot));
                    } else {
                        breaker.stop();
                    }
                }), () -> FluentContext.updateExecutorBoolean.set(false));
    }
}
