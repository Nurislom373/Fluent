package org.khasanof.executors;

import org.khasanof.FluentBot;
import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.custom.BreakerForEach;
import org.khasanof.context.FluentThreadLocalContext;
import org.khasanof.executors.invoker.InvokerExecutor;
import org.khasanof.executors.invoker.DefaultInvokerFunctions;
import org.khasanof.executors.invoker.InvokerFunctionsAdaptee;
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

    private final InvokerFunctionsAdaptee invokerFunctionsAdaptee; // specifies the methods that can be invoked.
    private final DeterminationUpdate determination; // gathers the methods corresponding to the incoming update.
    private final InvokerExecutor invoker; // method invoker
    private final GenericSingleton<FluentBot> fluentBot; // bot instance

    public CommonUpdateExecutor(DefaultInvokerFunctions invokerFunctions, DeterminationUpdate determinationUpdateType,
                                InvokerExecutor invoker, GenericSingleton<FluentBot> fluentBot) {
        this.invokerFunctionsAdaptee = invokerFunctions;
        this.determination = determinationUpdateType;
        this.fluentBot = fluentBot;
        this.invoker = invoker;
    }

    /**
     * Using the {@link DeterminationUpdate#determinationInvokers(Update)} method, update collects the matching methods.
     * iterates the collected methods one by one. If an error or something happens, the execution of methods is stopped.
     *
     * @param update from telegram is coming.
     */
    @Override
    public void execute(Update update) {
        FluentBot instance = checkBotInstance();
        BreakerForEach.forEach(determination.determinationInvokers(update).stream(),
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
