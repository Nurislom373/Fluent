package org.khasanof.executors.determination;

import org.khasanof.models.invoker.SimpleInvoker;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 8/5/2023 9:02 PM
 */
public interface DeterminationService {

    List<BiConsumer<Update, Set<SimpleInvoker>>> getDeterminations();

}
