package org.khasanof.executors;

import org.khasanof.FluentBot;
import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.custom.BreakerForEach;
import org.khasanof.custom.FluentContext;
import org.khasanof.executors.invoker.Invoker;
import org.khasanof.executors.invoker.InvokerFunctions;
import org.khasanof.executors.invoker.InvokerFunctionsImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 24.06.2023 0:46
 * @version 1.0.0
 */
@Component
public class CommonUpdateExecutor extends AbstractUpdateExecutor {

    private final InvokerFunctions invokerFunctions; // specifies the methods that can be invoked.
    private final DeterminationUpdate determination; // gathers the methods corresponding to the incoming update.
    private final Invoker invoker; // method invoker
    private final GenericSingleton<FluentBot> fluentBot; // bot instance

    public CommonUpdateExecutor(InvokerFunctionsImpl invokerFunctions, DeterminationUpdate determinationUpdateType,
                                Invoker invoker, GenericSingleton<FluentBot> fluentBot) {
        this.invokerFunctions = invokerFunctions;
        this.determination = determinationUpdateType;
        this.fluentBot = fluentBot;
        this.invoker = invoker;
    }

    /**
     * Using the {@link DeterminationUpdate#determinationV2(Update)} method, update collects the matching methods.
     * iterates the collected methods one by one. If an error or something happens, the execution of methods is stopped.
     *
     * @param update from telegram is coming.
     */
    @Override
    public void execute(Update update) {
        FluentBot instance = checkBotInstance();
        BreakerForEach.forEach(determination.determinationV2(update).stream(),
                ((entry, breaker) -> {
                    if (!FluentContext.updateExecutorBoolean.get()) {
                        invoker.invoke(invokerFunctions.fillAndGet(entry, update, instance));
                    } else {
                        breaker.stop();
                    }
                }), () -> FluentContext.updateExecutorBoolean.set(false));
    }

    private FluentBot checkBotInstance() {
        if (Objects.isNull(fluentBot.getInstance())) {
            throw new RuntimeException("Bot instance null!");
        }
        return fluentBot.getInstance();
    }

}
