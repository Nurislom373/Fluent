package org.khasanof.executors.determination;

import org.khasanof.config.DefineProcessType;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 16.07.2023 17:43
 */
public interface DeterminationFunction extends DefineProcessType {

    BiConsumer<Update, Set<SimpleInvoker>> accept(ApplicationContext applicationContext);

    Integer getOrder();

}
