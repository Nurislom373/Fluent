package org.khasanof.executors;

import org.jetbrains.annotations.NotNull;
import org.khasanof.custom.BreakerForEach;
import org.khasanof.context.FluentThreadLocalContext;
import org.khasanof.executors.determination.DeterminationService;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 24.06.2023 20:00
 */
@Component
public class DeterminationUpdate {

    private final DeterminationService determinationService;

    public DeterminationUpdate(DeterminationService determinationService) {
        this.determinationService = determinationService;
    }

    public Set<SimpleInvoker> determinationInvokers(Update update) {
        return getSimpleInvokers(update);
    }

    @NotNull
    private Set<SimpleInvoker> getSimpleInvokers(Update update) {
        Set<SimpleInvoker> invokerResults = new LinkedHashSet<>();
        assembleInvokers(update, invokerResults);
        return invokerResults;
    }

    private void assembleInvokers(Update update, Set<SimpleInvoker> invokerResults) {
        BreakerForEach.forEach(determinationService.getDeterminations().stream(),
                ((determination, breaker) -> determinationAccept(update, invokerResults, determination)),
                () -> FluentThreadLocalContext.determinationServiceBoolean.set(false));
    }

    private void determinationAccept(Update update, Set<SimpleInvoker> invokerResults, BiConsumer<Update, Set<SimpleInvoker>> determination) {
        if (!FluentThreadLocalContext.determinationServiceBoolean.get()) {
            determination.accept(update, invokerResults);
        } else {
            FluentThreadLocalContext.determinationServiceBoolean.set(true);
        }
    }

}
