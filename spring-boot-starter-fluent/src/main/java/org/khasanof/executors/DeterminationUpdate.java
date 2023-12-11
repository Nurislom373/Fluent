package org.khasanof.executors;

import org.khasanof.custom.BreakerForEach;
import org.khasanof.custom.FluentContext;
import org.khasanof.executors.determination.DeterminationService;
import org.khasanof.model.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.LinkedHashSet;
import java.util.List;
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

    public Set<SimpleInvoker> determinationV2(Update update) {
        Set<SimpleInvoker> invokerResults = new LinkedHashSet<>();
        List<BiConsumer<Update, Set<SimpleInvoker>>> list = determinationService.getDeterminations();
        BreakerForEach.forEach(list.stream(), ((updateMapBiConsumer, breaker) -> {
            if (!FluentContext.determinationServiceBoolean.get()) {
                updateMapBiConsumer.accept(update, invokerResults);
            } else {
                FluentContext.determinationServiceBoolean.set(true);
            }
        }), () -> FluentContext.determinationServiceBoolean.set(false));
        return invokerResults;
    }

}
