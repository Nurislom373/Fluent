package org.khasanof.models.processor;

import lombok.*;
import org.khasanof.executors.determination.DeterminationFunction;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.models.processor
 * @since 2/25/2024 8:56 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SimpleInvokerFillerModel {

    private Update update;
    private ApplicationContext applicationContext;
    private DeterminationFunction determinationFunction;

    /**
     *
     * @param simpleInvokers
     */
    public void accept(Set<SimpleInvoker> simpleInvokers) {
        determinationFunction.getConsumer(applicationContext)
                .accept(update, simpleInvokers);
    }
}
